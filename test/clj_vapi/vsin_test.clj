(ns clj-vapi.vsin-test
  (:require [clj-vapi.core :refer [vsin]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.math :as math]
            [clojure.test :refer [deftest testing]]))

(def epsilon 1e-6)

(def angles [0 (/ math/PI 6) (/ math/PI 4) (/ math/PI 3) (/ math/PI 2) math/PI])
(def angles-sin (map math/sin angles))

(defn run-vsin-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor angles-sin)
   (vsin (array-ctor angles))))

(deftest vsin-test
  (testing "vsin"
    (run-vsin-tests float-array)
    (run-vsin-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (let [angles-2-by-3 (vec (map vec (partition 3 angles)))
        asin-2-by-3 (vec (map vec (partition 3 angles-sin)))]
    (tu/equalish-2d-arrays?
     epsilon
     (vsin (u/vec->array array-ctor angles-2-by-3))
     (u/vec->array array-ctor asin-2-by-3))))

(deftest vsin-2d-test
  (testing "vsin-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

