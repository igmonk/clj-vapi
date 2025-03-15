(ns clj-vapi.vtest-neg-test
  (:require [clojure.test :refer :all]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vtest-neg?]]))

(def ones (take 10 (repeat 1)))
(def neg-ones (take 10 (repeat -1)))
(def neg-pos-ones [-1 1 -1 1 -1 1 -1 1 -1 1])

(def range-10-true (take 10 (repeat true)))
(def range-10-false (take 10 (repeat false)))
(def true-false-5x
  (mapcat identity (take 5 (repeat [true false]))))

(defn run-vtest-neg?-test
  [array-ctor]
  (tu/equal-arrays? (boolean-array range-10-false)
                    (vtest-neg? (array-ctor ones)))
  (tu/equal-arrays? (boolean-array range-10-true)
                    (vtest-neg? (array-ctor neg-ones)))
  (tu/equal-arrays? (boolean-array true-false-5x)
                    (vtest-neg? (array-ctor neg-pos-ones))))

(deftest vtest-neg-test
  (testing "vtest-neg?"
    (run-vtest-neg?-test byte-array)
    (run-vtest-neg?-test short-array)
    (run-vtest-neg?-test int-array)
    (run-vtest-neg?-test long-array)
    (run-vtest-neg?-test float-array)
    (run-vtest-neg?-test double-array)))

(defn run-vtest-neg?-2d-tests
  [array-ctor]
  (let [sample-2d [[-10 0 10]
                   [-20 0 20]
                   [-30 0 30]]
        bool-2d [[true false false]
                 [true false false]
                 [true false false]]]
    (tu/equal-2d-arrays?
     (u/vec->array boolean-array bool-2d)
     (vtest-neg? (u/vec->array array-ctor sample-2d)))))

(deftest vtest-neg?-2d-test
  (testing "vtest-neg? 2d"
    (run-vtest-neg?-2d-tests byte-array)
    (run-vtest-neg?-2d-tests short-array)
    (run-vtest-neg?-2d-tests int-array)
    (run-vtest-neg?-2d-tests long-array)
    (run-vtest-neg?-2d-tests float-array)
    (run-vtest-neg?-2d-tests double-array)))

