(ns clj-vapi.vprod-test
  (:require [clj-vapi.core :refer [vprod]]
            [clj-vapi.utils :as u]
            [clojure.test :refer [deftest is testing]]))

(def range-1-11 (range 1 11))

(deftest vprod-test
  (testing "vprod-byte"
    (is (= 3628800 (vprod (byte-array range-1-11)))))

  (testing "vprod-int"
    (is (= 3628800 (vprod (int-array range-1-11)))))

  (testing "vprod-long"
    (is (= 3628800 (vprod (long-array range-1-11)))))

  (testing "vprod-float"
    (is (= 3628800.0 (vprod (float-array range-1-11)))))

  (testing "vprod-double"
    (is (= 3628800.0 (vprod (double-array range-1-11))))))

(defn run-2d-int-tests
  [array-ctor]
  (is (= (vprod (array-ctor 2 2 1)) 1))
  (is (= (vprod (array-ctor 2 2 2)) 16))
  (is (= (vprod (array-ctor 2 2 3)) 81)))

(defn run-2d-floating-tests
  [array-ctor]
  (is (= (vprod (array-ctor 2 2 1.0)) 1.0))
  (is (= (vprod (array-ctor 2 2 2.0)) 16.0))
  (is (= (vprod (array-ctor 2 2 3.0)) 81.0)))

(deftest vprod-2d-test
  (testing "vprod-2d integral"
    (run-2d-int-tests u/byte-2d-array)
    (run-2d-int-tests u/int-2d-array)
    (run-2d-int-tests u/long-2d-array))

  (testing "vprod-2d floating"
    (run-2d-floating-tests u/float-2d-array)
    (run-2d-floating-tests u/double-2d-array)))

