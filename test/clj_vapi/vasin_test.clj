(ns clj-vapi.vasin-test
  (:require [clojure.test :refer :all]
            [clojure.math :as math]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vasin]]))

(def epsilon 1e-6)

(def values (map #(/ 1 %) (range 1 5)))
(def angles (map math/asin values))

(defn run-vasin-tests
  [array-ctor]
  (tu/equalish-arrays?
     epsilon
     (array-ctor angles)
     (vasin (array-ctor values))))

(deftest vasin-test
  (testing "vasin"
    (run-vasin-tests float-array)
    (run-vasin-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (let [vals-2-by-2 (vec (map vec (partition 2 values)))
        angles-2-by-2 (vec (map vec (partition 2 angles)))]
    (tu/equalish-2d-arrays?
     epsilon
     (vasin (u/vec->array array-ctor vals-2-by-2))
     (u/vec->array array-ctor angles-2-by-2))))

(deftest vasin-2d-test
  (testing "vasin-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

