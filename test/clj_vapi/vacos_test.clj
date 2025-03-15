(ns clj-vapi.vacos-test
  (:require [clojure.test :refer :all]
            [clojure.math :as math]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vacos]]))

(def epsilon 1e-6)

(def values (map #(/ 1 %) (range 1 5)))
(def angles (map math/acos values))

(defn run-vacos-tests
  [array-ctor]
  (tu/equalish-arrays?
     epsilon
     (array-ctor angles)
     (vacos (array-ctor values))))

(deftest vacos-test
  (testing "vacos"
    (run-vacos-tests float-array)
    (run-vacos-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (let [vals-2-by-2 (vec (map vec (partition 2 values)))
        angles-2-by-2 (vec (map vec (partition 2 angles)))]
    (tu/equalish-2d-arrays?
     epsilon
     (vacos (u/vec->array array-ctor vals-2-by-2))
     (u/vec->array array-ctor angles-2-by-2))))

(deftest vacos-2d-test
  (testing "vacos-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

