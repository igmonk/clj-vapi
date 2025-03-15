(ns clj-vapi.vsum-test
  (:require [clojure.test :refer :all]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vsum]]))

(def range-1-11 (range 1 11))

(deftest vsum-test
  (testing "vsum-byte"
    (is (= 55 (vsum (byte-array range-1-11)))))

  (testing "vsum-short"
    (is (= 55 (vsum (short-array range-1-11)))))

  (testing "vsum-int"
    (is (= 55 (vsum (int-array range-1-11)))))

  (testing "vsum-long"
    (is (= 55 (vsum (long-array range-1-11)))))

  (testing "vsum-float"
    (is (= 55.0 (vsum (float-array range-1-11)))))

  (testing "vsum-double"
    (is (= 55.0 (vsum (double-array range-1-11))))))

(defn run-2d-int-tests
  [array-ctor]
  (is (= (vsum (array-ctor 4 4 1)) 16))
  (is (= (vsum (array-ctor 4 4 2)) 32))
  (is (= (vsum (array-ctor 4 4 3)) 48)))

(defn run-2d-floating-tests
  [array-ctor]
  (is (= (vsum (array-ctor 4 4 1.0)) 16.0))
  (is (= (vsum (array-ctor 4 4 2.0)) 32.0))
  (is (= (vsum (array-ctor 4 4 3.0)) 48.0)))

(deftest vsum-2d-test
  (testing "vsum-2d integral"
    (run-2d-int-tests u/byte-2d-array)
    (run-2d-int-tests u/short-2d-array)
    (run-2d-int-tests u/int-2d-array)
    (run-2d-int-tests u/long-2d-array))

  (testing "vsum-2d floating"
    (run-2d-floating-tests u/float-2d-array)
    (run-2d-floating-tests u/double-2d-array)))

