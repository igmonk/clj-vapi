(ns clj-vapi.vatan-test
  (:require [clojure.test :refer :all]
            [clojure.math :as math]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vatan]]))

(def epsilon 1e-6)

(def values (map #(/ 1 %) (range 1 5)))
(def angles (map math/atan values))

(defn run-vatan-tests
  [array-ctor]
  (tu/equalish-arrays?
     epsilon
     (array-ctor angles)
     (vatan (array-ctor values))))

(deftest vatan-test
  (testing "vatan"
    (run-vatan-tests float-array)
    (run-vatan-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (let [vals-2-by-2 (vec (map vec (partition 2 values)))
        angles-2-by-2 (vec (map vec (partition 2 angles)))]
    (tu/equalish-2d-arrays?
     epsilon
     (vatan (u/vec->array array-ctor vals-2-by-2))
     (u/vec->array array-ctor angles-2-by-2))))

(deftest vatan-2d-test
  (testing "vatan-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

