(ns clj-vapi.vcbrt-test
  (:require [clj-vapi.core :refer [vcbrt]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.math :as math]
            [clojure.test :refer [deftest testing]]))

(def epsilon 1e-6)

(def range-1-11 (map float (range 1 11)))
(def range-1-11-cb (map tu/cube range-1-11))
(def range-1-11-cbrt (map math/cbrt range-1-11-cb))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor range-1-11-cbrt)
   (vcbrt (array-ctor range-1-11-cb))))

(deftest vcbrt-test
  (testing "vcbrt floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vcbrt (u/vec->array array-ctor [[-1.0 0.0] [-8.0 27.0]]))
   (u/vec->array array-ctor [[-1.0 0.0] [-2.0 3.0]])))

(deftest vcbrt-2d-test
  (testing "vcbrt-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

