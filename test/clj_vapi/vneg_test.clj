(ns clj-vapi.vneg-test
  (:require [clojure.test :refer :all]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vneg]]))

(def epsilon 1e-6)

(def range-1-11 (range 1 11))
(def range-1-11-neg (map - range-1-11))

(defn run-integral-tests
  [array-ctor]
  (tu/equal-arrays?
   (array-ctor range-1-11-neg)
   (vneg (array-ctor range-1-11)))
  (tu/equal-arrays?
   (array-ctor range-1-11)
   (vneg (array-ctor range-1-11-neg))))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor range-1-11-neg)
   (vneg (array-ctor range-1-11)))
  (tu/equalish-arrays?
   epsilon
   (array-ctor range-1-11)
   (vneg (array-ctor range-1-11-neg))))

(deftest vneg-test
  (testing "vneg integral"
    (run-integral-tests byte-array)
    (run-integral-tests short-array)
    (run-integral-tests int-array)
    (run-integral-tests long-array))

  (testing "vneg floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-int-2d-tests
  [array-ctor]
  (tu/equal-2d-arrays?
   (vneg (u/vec->array array-ctor [[-1 0] [-2 1]]))
   (u/vec->array array-ctor [[1 0] [2 -1]])))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vneg (u/vec->array array-ctor [[-1.0 0.0] [-2.0 1.0]]))
   (u/vec->array array-ctor [[1.0 -0.0] [2.0 -1.0]])))

(deftest vneg-2d-test
  (testing "vneg-2d integral"
    (run-int-2d-tests byte-array)
    (run-int-2d-tests short-array)
    (run-int-2d-tests int-array)
    (run-int-2d-tests long-array))

  (testing "vneg-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

