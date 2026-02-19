(ns clj-vapi.vtest-ne-test
  (:require [clj-vapi.core :refer [vtest-ne?]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.test :refer [deftest testing]]))

(def range-1-11 (range 1 11))
(def range-2-12 (range 2 12))
(def odds-zeros [1 0 3 0 5 0 7 0 9 0])

(def range-10-true (take 10 (repeat true)))
(def range-10-false (take 10 (repeat false)))
(def false-true-5x
  (mapcat identity (take 5 (repeat [false true]))))

(defn run-vtest-ne?-test
  [array-ctor]
  (tu/equal-arrays? (boolean-array range-10-false)
                    (vtest-ne? (array-ctor range-1-11)
                               (array-ctor range-1-11)))
  (tu/equal-arrays? (boolean-array range-10-true)
                    (vtest-ne? (array-ctor range-1-11)
                               (array-ctor range-2-12)))
  (tu/equal-arrays? (boolean-array false-true-5x)
                    (vtest-ne? (array-ctor range-1-11)
                               (array-ctor odds-zeros))))

(deftest vtest-ne-test
  (testing "vtest-ne?"
    (run-vtest-ne?-test byte-array)
    (run-vtest-ne?-test short-array)
    (run-vtest-ne?-test int-array)
    (run-vtest-ne?-test long-array)
    (run-vtest-ne?-test float-array)
    (run-vtest-ne?-test double-array)))

(defn run-vtest-ne?-2d-tests
  [array-ctor]
  (let [sample1-2d [[-10 0 10]
                    [-20 0 20]
                    [-30 0 30]]
        sample2-2d [[-10 0 10]
                    [-21 0 21]
                    [100 1 10]]
        bool-2d [[false false false]
                 [true false true]
                 [true true true]]]
    (tu/equal-2d-arrays?
     (u/vec->array boolean-array bool-2d)
     (vtest-ne? (u/vec->array array-ctor sample1-2d)
                (u/vec->array array-ctor sample2-2d)))))

(deftest vtest-ne?-2d-test
  (testing "vtest-ne? 2d"
    (run-vtest-ne?-2d-tests byte-array)
    (run-vtest-ne?-2d-tests short-array)
    (run-vtest-ne?-2d-tests int-array)
    (run-vtest-ne?-2d-tests long-array)
    (run-vtest-ne?-2d-tests float-array)
    (run-vtest-ne?-2d-tests double-array)))

