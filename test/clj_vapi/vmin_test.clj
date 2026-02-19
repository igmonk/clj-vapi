(ns clj-vapi.vmin-test
  (:require [clj-vapi.core :refer [vmin]]
            [clojure.test :refer [deftest is testing]]))

(def range-1-11 (range 1 11))
(def range-11-1 (reverse range-1-11))

(defn run-integral-tests
  [array-ctor]
  (is (nil? (vmin (array-ctor 0))))
  (is (= 1 (vmin (array-ctor range-1-11))))
  (is (= 1 (vmin (array-ctor range-11-1)))))

(defn run-floating-tests
  [array-ctor]
  (is (nil? (vmin (array-ctor 0))))
  (is (= 1.0 (vmin (array-ctor range-1-11))))
  (is (= 1.0 (vmin (array-ctor range-11-1)))))

(deftest vmin-test
  (testing "vmin integral"
    (run-integral-tests byte-array)
    (run-integral-tests short-array)
    (run-integral-tests int-array)
    (run-integral-tests long-array))

  (testing "vsum floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

