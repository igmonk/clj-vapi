(ns clj-vapi.vprod-test
  (:require [clojure.test :refer :all]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vprod]]))

(def range-1-11 (range 1 11))

(deftest vprod-test
  (testing "vprod-byte"
    (is (= 3628800 (vprod (byte-array range-1-11)))))

  ;; Note: Overflow
  ;; (1*2*3*4*5*6*7*8-32767) - 32768 - 1 = -25216
  ;; Short.MAX_VALUE = 32767
  ;; Short.MIN_VALUE = -32768
  ;; Consider using Vector.convert or Vector.convertShape fn.
  (testing "vprod-short"
    (is (= -25216 (vprod (short-array (range 1 9))))))

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
    (run-2d-int-tests u/short-2d-array)
    (run-2d-int-tests u/int-2d-array)
    (run-2d-int-tests u/long-2d-array))

  (testing "vprod-2d floating"
    (run-2d-floating-tests u/float-2d-array)
    (run-2d-floating-tests u/double-2d-array)))

