(ns clj-vapi.vpow-test
  (:require [clojure.test :refer :all]
            [clojure.math :as math]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vpow]]))

(def epsilon 1e-6)
(def range-1-11 (map float (range 1 11)))

(defn raise-to [e coll] (map #(math/pow % e) coll))

(defn test-vpow-integral
  [array-ctor e]
  (tu/equal-arrays?
   (array-ctor (raise-to e range-1-11))
   (vpow (array-ctor range-1-11) e)))

(defn test-vpow-floating
  [array-ctor e]
  (tu/equalish-arrays?
   epsilon
   (array-ctor (raise-to e range-1-11))
   (vpow (array-ctor range-1-11) e)))

(defn run-integral-tests
  [array-ctor]
  (test-vpow-integral array-ctor 2)
  (test-vpow-integral array-ctor 3))

(defn run-floating-tests
  [array-ctor]
  (test-vpow-floating array-ctor (float 0.5))
  (test-vpow-floating array-ctor -1))

(deftest vpow-test
  (testing "vpow integral"
    (run-integral-tests float-array)
    (run-integral-tests double-array))

  (testing "vpow floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vpow (u/vec->array array-ctor [[1.0 0.0] [2.0 -1.0]]) 1)
   (u/vec->array array-ctor [[1.0 0.0] [2.0 -1.0]]))
  (tu/equalish-2d-arrays?
   epsilon
   (vpow (u/vec->array array-ctor [[1.0 0.0] [2.0 -1.0]]) 2)
   (u/vec->array array-ctor [[1.0 0.0] [4.0 1.0]]))
  (tu/equalish-2d-arrays?
   epsilon
   (vpow (u/vec->array array-ctor [[1.0 0.0] [2.0 -1.0]]) 3)
   (u/vec->array array-ctor [[1.0 0.0] [8.0 -1.0]])))

(deftest vpow-2d-test
  (testing "vpow-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

