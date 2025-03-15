(ns clj-vapi.vsqrt-test
  (:require [clojure.test :refer :all]
            [clojure.math :as math]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vsqrt]]))

(def epsilon 1e-6)

(def range-1-11 (map float (range 1 11)))
(def range-1-11-sq (map tu/square range-1-11))
(def range-1-11-sqrt (map math/sqrt range-1-11-sq))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
     epsilon
     (array-ctor range-1-11-sqrt)
     (vsqrt (array-ctor range-1-11-sq))))

(deftest vsqrt-test
  (testing "vsqrt floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vsqrt (u/vec->array array-ctor [[1.0 0.0] [4.0 9.0]]))
   (u/vec->array array-ctor [[1.0 0.0] [2.0 3.0]])))

(deftest vsqrt-2d-test
  (testing "vsqrt-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

