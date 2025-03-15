(ns clj-vapi.vadd-test
  (:require [clojure.test :refer :all]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vadd]]))

(def epsilon 1e-6)

(def range-1-11 (range 1 11))
(def range-2-12 (range 2 12))
(def range-sum (map + range-1-11 range-2-12))

(defn run-integral-tests
  [array-ctor]
  (tu/equal-arrays?
   (array-ctor range-sum)
   (vadd (array-ctor range-1-11)
         (array-ctor range-2-12))))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor range-sum)
   (vadd (array-ctor range-1-11)
         (array-ctor range-2-12))))

(deftest vadd-test
  (testing "vadd integral"
    (run-integral-tests byte-array)
    (run-integral-tests short-array)
    (run-integral-tests int-array)
    (run-integral-tests long-array))

  (testing "vadd floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-int-2d-tests
  [array-ctor]
  (tu/equal-2d-arrays?
   (vadd (array-ctor 2 2 1)
         (array-ctor 2 2 2))
   (array-ctor 2 2 3)))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vadd (array-ctor 2 2 1.0)
         (array-ctor 2 2 2.0))
   (array-ctor 2 2 3.0)))

(deftest vadd-2d-test
  (testing "vadd-2d integral"
    (run-int-2d-tests u/byte-2d-array)
    (run-int-2d-tests u/short-2d-array)
    (run-int-2d-tests u/int-2d-array)
    (run-int-2d-tests u/long-2d-array))

  (testing "vadd-2d floating"
    (run-floating-2d-tests u/float-2d-array)
    (run-floating-2d-tests u/double-2d-array)))

