(ns clj-vapi.vlog-test
  (:require [clojure.test :refer :all]
            [clojure.math :as math]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vlog]]))

(def epsilon 1e-6)

(def range-1-11 (map float (range 1 11)))
(def range-1-11-exp (map math/exp range-1-11))
(def range-1-11-log (map math/log range-1-11-exp))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
     epsilon
     (array-ctor range-1-11-log)
     (vlog (array-ctor range-1-11-exp))))

(deftest vlog-test
  (testing "vlog floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vlog (u/vec->array array-ctor [[1.0 math/E]
                                   [(math/exp 2) (math/exp 3)]]))
   (u/vec->array array-ctor [[0.0 1.0] [2.0 3.0]])))

(deftest vlog-2d-test
  (testing "vlog-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

