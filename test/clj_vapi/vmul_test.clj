(ns clj-vapi.vmul-test
  (:require [clj-vapi.core :refer [vmul]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.test :refer [deftest testing]]))

(def epsilon 1e-6)

(def range-1-11 (range 1 11))
(def range-2-12 (range 2 12))
(def range-prod (map * range-1-11 range-2-12))

(defn run-integral-tests
  [array-ctor]
  (tu/equal-arrays?
   (array-ctor range-prod)
   (vmul (array-ctor range-1-11)
         (array-ctor range-2-12))))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor range-prod)
   (vmul (array-ctor range-1-11)
         (array-ctor range-2-12))))

(deftest vmul-test
  (testing "vmul integral"
    (run-integral-tests byte-array)
    (run-integral-tests short-array)
    (run-integral-tests int-array)
    (run-integral-tests long-array))

  (testing "vmul floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

(defn run-int-2d-tests
  [array-ctor]
  (tu/equal-2d-arrays?
   (vmul (array-ctor 2 2 2)
         (array-ctor 2 2 3))
   (array-ctor 2 2 6)))

(defn run-floating-2d-tests
  [array-ctor]
  (tu/equalish-2d-arrays?
   epsilon
   (vmul (array-ctor 2 2 2.0)
         (array-ctor 2 2 3.0))
   (array-ctor 2 2 6.0)))

(deftest vmul-2d-test
  (testing "vmul-2d integral"
    (run-int-2d-tests u/byte-2d-array)
    (run-int-2d-tests u/short-2d-array)
    (run-int-2d-tests u/int-2d-array)
    (run-int-2d-tests u/long-2d-array))

  (testing "vmul-2d floating"
    (run-floating-2d-tests u/float-2d-array)
    (run-floating-2d-tests u/double-2d-array)))

