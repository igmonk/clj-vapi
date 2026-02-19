(ns clj-vapi.vmax-test
  (:require [clj-vapi.core :refer [vmax]]
            [clojure.test :refer [deftest is testing]]))

(def range-1-11 (range 1 11))
(def range-11-1 (reverse range-1-11))

(defn run-integral-tests
  [array-ctor]
  (is (nil? (vmax (array-ctor 0))))
  (is (= 10 (vmax (array-ctor range-1-11))))
  (is (= 10 (vmax (array-ctor range-11-1)))))

(defn run-floating-tests
  [array-ctor]
  (is (nil? (vmax (array-ctor 0))))
  (is (= 10.0 (vmax (array-ctor range-1-11))))
  (is (= 10.0 (vmax (array-ctor range-11-1)))))

(deftest vmax-test
  (testing "vmax integral"
    (run-integral-tests byte-array)
    (run-integral-tests short-array)
    (run-integral-tests int-array)
    (run-integral-tests long-array))

  (testing "vsum floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

