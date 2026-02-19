(ns clj-vapi.vexpm1-test
  (:require [clj-vapi.core :refer [vexpm1]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.math :as math]
            [clojure.test :refer [deftest testing]]))

(def epsilon 1e-6)

(def range-1-11 (range 1 11))
(def range-1-11-expm1 (map math/expm1 range-1-11))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor range-1-11-expm1)
   (vexpm1 (array-ctor range-1-11))))

(deftest vexpm1-test
  (testing "vexpm1 floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vexpm1 (u/vec->array array-ctor [[1.0 0.0] [2.0 -1.0]]))
   (u/vec->array array-ctor [[(dec math/E) 0.0]
                             [(dec (math/exp 2.0)) (dec (math/exp -1.0))]])))

(deftest vexpm1-2d-test
  (testing "vexpm1-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

