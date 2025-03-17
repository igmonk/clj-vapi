(ns clj-vapi.matrix-ops
  (:require [criterium.core :as c]
            [clj-vapi.utils :as u]
            [clj-vapi.vector-ops :refer [vdot2*]]))

;; ------------------ Matrix Transposition ------------------

(defn transpose
  "Returns a new matrix that is the traspose of the given one."
  [m]
  (let [rows (count m)
        cols (apply max (map count m))
        {:keys [ty aset-fn]} (u/array-meta m)
        mt (make-array ty cols rows)]
    (dotimes [i cols]
      (dotimes [j rows]
        (aset-fn mt i j (aget m j i))))
    mt))

;; ------------------ Matrix Multiplication ------------------

(defn m*
  "Returns a new matrix that is the product of the given two.
  The applicability of matrix multiplication is assumed.
  The function replaces the matrix on the RHS of the * operation
  with its transpose, and then applies the vectorized dot product
  defined on 1d arrays."
  [m1 m2]
  (let [m2t (transpose m2)
        rows (count m1)
        cols (count m2t)
        {:keys [ty aset-fn]} (u/array-meta m2t)
        res (make-array ty rows cols)]
    (dotimes [i rows]
      (dotimes [j cols]
        (aset-fn res i j
                 (vdot2* (aget m1 i) (aget m2t j)))))
    res))

;; ------------------ Matrix Ops Protocol ------------------

(defprotocol MatrixOps
  (vm* [m1 m2]))

(extend-protocol MatrixOps
  float/2
  (vm* [m1 m2] (m* m1 m2)))

;; ------------------ Usage ------------------

(def m1 (u/vec->float-array [[1 2 3]
                             [4 5 6]
                             [7 8 9]]))

(vm* m1 m1)
;; =>
;; [[30.0 36.0 42.0]
;;  [66.0 81.0 96.0]
;;  [102.0 126.0 150.0]]

;; ------------------ Benchmarks ------------------

(def floats-2d (u/float-2d-array 10 10 2.0))

(c/quick-bench (vm* floats-2d floats-2d))

