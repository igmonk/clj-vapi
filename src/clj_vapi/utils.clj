(ns clj-vapi.utils)

(defrecord AMeta [ty aset-fn])

(defn array-meta
  [a]
  (letfn [(base-type [ty]
            (cond
              (= ty byte/1) (->AMeta Byte/TYPE aset-byte)
              (= ty short/1) (->AMeta Short/TYPE aset-short)
              (= ty int/1) (->AMeta Integer/TYPE aset-int)
              (= ty long/1) (->AMeta Long/TYPE aset-long)
              (= ty float/1) (->AMeta Float/TYPE aset-float)
              (= ty double/1) (->AMeta Double/TYPE aset-double)
              (.isArray ty) (recur (.getComponentType ty))
              :else
              (throw (IllegalArgumentException.
                      (str "Unsupported array type: " ty)))))]
    (base-type (type a))))

(defn init-2d-array
  [x data]
  (let [aset-fn (:aset-fn (array-meta x))]
    (doseq [i (range (count data))
            j (range (count (data i)))]
      (aset-fn x i j (get-in data [i j])))))

(defn array-2d
  ([ty m n] (make-array ty m n))
  ([ty m n init]
   (let [a (array-2d ty m n)
         aset-fn (:aset-fn (array-meta a))]
     (doseq [i (range (alength a))
             j (range (alength (aget a i)))]
       (aset-fn a i j init))
     a)))

(def byte-2d-array (partial array-2d Byte/TYPE))
(def short-2d-array (partial array-2d Short/TYPE))
(def int-2d-array (partial array-2d Integer/TYPE))
(def long-2d-array (partial array-2d Long/TYPE))
(def float-2d-array (partial array-2d Float/TYPE))
(def double-2d-array (partial array-2d Double/TYPE))

(defn vec->array [array-ctor v]
  (letfn [(build-array [data]
            (if (vector? (first data))
              (into-array (map build-array data))
              (array-ctor data)))]
    (build-array v)))

(def vec->byte-array (partial vec->array byte-array))
(def vec->short-array (partial vec->array short-array))
(def vec->int-array (partial vec->array int-array))
(def vec->long-array (partial vec->array long-array))
(def vec->float-array (partial vec->array float-array))
(def vec->double-array (partial vec->array double-array))

