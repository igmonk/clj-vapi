(ns clj-vapi.vlog1p-test
  (:require [clj-vapi.core :refer [vlog1p]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.math :as math]
            [clojure.test :refer [deftest testing]]))

(def epsilon (math/pow 10 -9))

(def range-1-11 (map - (range 1 11)))
(def range-1-11-powers (map #(math/pow 10 %) range-1-11))
(def range-1-11-log1p (map math/log1p range-1-11-powers))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor range-1-11-log1p)
   (vlog1p (array-ctor range-1-11-powers))))

(deftest vlog1p-test
  (testing "vlog1p floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vlog1p (u/vec->array array-ctor [[0.0 (dec math/E)]
                                     [0.0 (dec (math/exp 2))]]))
   (u/vec->array array-ctor [[0.0 1.0] [0.0 2.0]])))

(deftest vlog1p-2d-test
  (testing "vlog1p-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

