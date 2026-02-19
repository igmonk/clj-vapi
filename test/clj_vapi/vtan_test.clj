(ns clj-vapi.vtan-test
  (:require [clj-vapi.core :refer [vtan]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.math :as math]
            [clojure.test :refer [deftest testing]]))

(def epsilon 1e-6)

(def angles [0 (/ math/PI 6) (/ math/PI 4) (/ math/PI 3)])
(def angles-tan (map math/tan angles))

(defn run-vtan-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor angles-tan)
   (vtan (array-ctor angles))))

(deftest vtan-test
  (testing "vtan"
    (run-vtan-tests float-array)
    (run-vtan-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (let [angles-2-by-2 (vec (map vec (partition 2 angles)))
        atan-2-by-2 (vec (map vec (partition 2 angles-tan)))]
    (tu/equalish-2d-arrays?
     epsilon
     (vtan (u/vec->array array-ctor angles-2-by-2))
     (u/vec->array array-ctor atan-2-by-2))))

(deftest vtan-2d-test
  (testing "vtan-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

