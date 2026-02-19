(ns clj-vapi.vscale-test
  (:require [clj-vapi.core :refer [vscale]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.test :refer [deftest testing]]))

(def epsilon 1e-6)
(def range-1-11 (range 1 11))

(defn scale-by [s coll] (map #(* % s) coll))

(defn test-vscale-integral
  [array-ctor s]
  (tu/equal-arrays?
   (array-ctor (scale-by s range-1-11))
   (vscale (array-ctor range-1-11) s)))

(defn test-vscale-floating
  [array-ctor s]
  (tu/equalish-arrays?
   epsilon
   (array-ctor (scale-by s range-1-11))
   (vscale (array-ctor range-1-11) s)))

(defn run-integral-tests
  [array-ctor]
  (test-vscale-integral array-ctor 2)
  (test-vscale-integral array-ctor 3))

(defn run-floating-tests
  [array-ctor]
  (test-vscale-floating array-ctor (float 0.5))
  (test-vscale-floating array-ctor -1))

(deftest vscale-test
  (testing "vscale integral"
    (run-integral-tests byte-array)
    (run-integral-tests short-array)
    (run-integral-tests int-array)
    (run-integral-tests long-array)
    (run-integral-tests float-array)
    (run-integral-tests double-array))

  (testing "vscale floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-int-2d-tests
  [array-ctor]
  (tu/equal-2d-arrays?
   (vscale (u/vec->array array-ctor [[1 0] [2 -1]]) 1)
   (u/vec->array array-ctor [[1 0] [2 -1]]))
  (tu/equal-2d-arrays?
   (vscale (u/vec->array array-ctor [[1 0] [2 -1]]) 2)
   (u/vec->array array-ctor [[2 0] [4 -2]]))
  (tu/equal-2d-arrays?
   (vscale (u/vec->array array-ctor [[1 0] [2 -1]]) 3)
   (u/vec->array array-ctor [[3 0] [6 -3]])))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vscale (u/vec->array array-ctor [[1.0 0.0] [2.0 -1.0]]) 1)
   (u/vec->array array-ctor [[1.0 0.0] [2.0 -1.0]]))
  (tu/equalish-2d-arrays?
   epsilon
   (vscale (u/vec->array array-ctor [[1.0 0.0] [2.0 -1.0]]) 2)
   (u/vec->array array-ctor [[2.0 0.0] [4.0 -2.0]]))
  (tu/equalish-2d-arrays?
   epsilon
   (vscale (u/vec->array array-ctor [[1.0 0.0] [2.0 -1.0]]) 3)
   (u/vec->array array-ctor [[3.0 0.0] [6.0 -3.0]])))

(deftest vscale-2d-test
  (testing "vscale-2d integral"
    (run-int-2d-tests byte-array)
    (run-int-2d-tests short-array)
    (run-int-2d-tests int-array)
    (run-int-2d-tests long-array))
  (testing "vscale-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

