(ns clj-vapi.vcos-test
  (:require [clj-vapi.core :refer [vcos]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.math :as math]
            [clojure.test :refer [deftest testing]]))

(def epsilon 1e-6)

(def angles [0 (/ math/PI 6) (/ math/PI 4) (/ math/PI 3) (/ math/PI 2) math/PI])
(def angles-cos (map math/cos angles))

(defn run-vcos-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor angles-cos)
   (vcos (array-ctor angles))))

(deftest vcos-test
  (testing "vcos"
    (run-vcos-tests float-array)
    (run-vcos-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (let [angles-2-by-3 (vec (map vec (partition 3 angles)))
        acos-2-by-3 (vec (map vec (partition 3 angles-cos)))]
    (tu/equalish-2d-arrays?
     epsilon
     (vcos (u/vec->array array-ctor angles-2-by-3))
     (u/vec->array array-ctor acos-2-by-3))))

(deftest vcos-2d-test
  (testing "vcos-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

