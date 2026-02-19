(ns clj-vapi.macros
  (:require [clj-vapi.utils :refer [array-meta]]))

;; ------------------ utils ------------------

(defmacro with-vbindings
  [[v-type species idx & avs] & body]
  (let [bindings
        (mapcat (fn [[a v]]
                  [v `(. ~v-type fromArray ~species ~a ~idx)])
                (partition 2 avs))]
    `(let [~@bindings]
       ~@body)))

(defmacro with-arrays
  [[arrays & avs] & body]
  (let [bindings [arrays (vec (map first (partition 2 avs)))]]
    `(let [~@bindings]
       ~@body)))

;; ------------------ vreduce ------------------

(defmacro vreduce
  [v-type f v-expr idx ret init a v & avs]
  `(let [species# (. ~v-type SPECIES_PREFERRED)
         loop-bound# (.loopBound species# (alength ~a))]
     (loop [~idx (int 0) ~ret ~init]
       (if (< ~idx loop-bound#)
         (with-vbindings [~v-type species# ~idx ~a ~v ~@avs]
           (recur (+ ~idx (.length species#)) ~v-expr))
         ;; Tail cleanup
         (with-arrays [arrays# ~a ~v ~@avs]
           (let [aget-all# (fn [i#] (map (fn [a#] (aget a# i#)) arrays#))]
             (loop [~idx (int ~idx) acc# ~ret]
               (if (< ~idx (alength ~a))
                 (recur (inc ~idx) (apply ~f (cons acc# (aget-all# ~idx))))
                 acc#))))))))

;; ------------------ vmap ------------------

(defmacro vmap
  [v-type f v-expr a v & avs]
  `(vreduce ~v-type (vmap-reducer ~f idx# ~a)
            (do (.intoArray ~v-expr ret# idx#) ret#)
            idx# ret# (aclone ~a)
            ~a ~v ~@avs))

(defn vmap-reducer
  [f idx a]
  (let [aset-fn (:aset-fn (array-meta a))]
    (fn [acc & xs]
      (aset-fn acc idx (apply f xs))
      acc)))

;; ------------------ Cross-lane Reduction ------------------

(defmacro vreduce-lanes
  [v-type f v-op init a]
  `(vreduce ~v-type ~f (~f ret# (.reduceLanes v# ~v-op)) idx# ret# ~init ~a v#))

;; ------------------ Lane-wise Unary & Binary & Ternary ------------------

(defmacro lanewise
  ([v-type f v-op a]
   `(vmap ~v-type ~f (.lanewise v# ~v-op) ~a v#))
  ([v-type f v-op a1 a2]
   `(vmap ~v-type ~f (.lanewise v1# ~v-op v2#) ~a1 v1# ~a2 v2#))
  ([v-type f v-op a1 a2 a3]
   `(vmap ~v-type ~f (.lanewise v1# ~v-op v2# v3#) ~a1 v1# ~a2 v2# ~a3 v3#)))

;; ------------------ Lane-wise Binary Broadcast ------------------

(defmacro lanewise-bc
  [v-type f v-op a s]
  `(vmap ~v-type #(~f % ~s) (.lanewise v# ~v-op ~s) ~a v#))

;; ------------------ Lane-wise Unary & Binary Tests ------------------

(defmacro lanewise-test
  ([v-type f v-op a]
   `(vreduce ~v-type (bool-reducer ~f idx#)
             (do (.intoArray (.test v# ~v-op) ret# idx#) ret#)
             idx# ret# (boolean-array (alength ~a))
             ~a v#))
  ([v-type f v-op a1 a2]
   `(vreduce ~v-type (bool-reducer ~f idx#)
             (do (.intoArray (.compare v1# ~v-op v2#) ret# idx#) ret#)
             idx# ret# (boolean-array (alength ~a1))
             ~a1 v1# ~a2 v2#)))

(defn bool-reducer
  [f idx]
  (fn [acc & xs]
    (aset-boolean acc idx (apply f xs))
    acc))

