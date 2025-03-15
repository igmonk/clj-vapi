(ns clj-vapi.vexp-test
  (:require [clojure.test :refer :all]
            [clojure.math :as math]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vexp]]))

(def epsilon 1e-6)

(def range-1-11 (range 1 11))
(def range-1-11-exp (map math/exp range-1-11))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
     epsilon
     (array-ctor range-1-11-exp)
     (vexp (array-ctor range-1-11))))

(deftest vexp-test
  (testing "vexp floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vexp (u/vec->array array-ctor [[1.0 0.0] [2.0 -1.0]]))
   (u/vec->array array-ctor [[math/E 1.0]
                             [(math/exp 2.0) (math/exp -1.0)]])))

(deftest vexp-2d-test
  (testing "vexp-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

