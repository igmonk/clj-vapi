(ns clj-vapi.vlog10-test
  (:require [clj-vapi.core :refer [vlog10]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.math :as math]
            [clojure.test :refer [deftest testing]]))

(def epsilon 1e-6)

(def range-1-11 (map float (range 1 11)))
(def range-1-11-powers10 (map #(math/pow 10 %) range-1-11))
(def range-1-11-log10 (map math/log10 range-1-11-powers10))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor range-1-11-log10)
   (vlog10 (array-ctor range-1-11-powers10))))

(deftest vlog10-test
  (testing "vlog10 floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vlog10 (u/vec->array array-ctor [[1.0 10.0] [100.0 0.1]]))
   (u/vec->array array-ctor [[0.0 1.0] [2.0 -1.0]])))

(deftest vlog10-2d-test
  (testing "vlog10-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

