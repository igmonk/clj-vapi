(ns clj-vapi.vtest-ge-test
  (:require [clojure.test :refer :all]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vtest-ge?]]))

(def range-1-11 (range 1 11))
(def range-2-12 (range 2 12))

(def range-10-true (take 10 (repeat true)))
(def range-10-false (take 10 (repeat false)))
(def false-true-5x
  (mapcat identity (take 5 (repeat [false true]))))

(defn run-vtest-ge?-test
  [array-ctor]
  (tu/equal-arrays? (boolean-array range-10-true)
                    (vtest-ge? (array-ctor range-1-11)
                               (array-ctor range-1-11)))
  (tu/equal-arrays? (boolean-array range-10-false)
                    (vtest-ge? (array-ctor range-1-11)
                               (array-ctor range-2-12)))
  (tu/equal-arrays? (boolean-array range-10-true)
                    (vtest-ge? (array-ctor range-2-12)
                               (array-ctor range-1-11)))
  (tu/equal-arrays? (boolean-array false-true-5x)
                    (vtest-ge? (array-ctor range-1-11)
                               (array-ctor
                                (tu/pairwise-inc-keep range-1-11))))
  (tu/equal-arrays? (boolean-array false-true-5x)
                    (vtest-ge? (array-ctor range-1-11)
                               (array-ctor
                                (tu/pairwise-inc-dec range-1-11)))))

(deftest vtest-ge?-test
  (testing "vtest-ge?"
    (run-vtest-ge?-test byte-array)
    (run-vtest-ge?-test short-array)
    (run-vtest-ge?-test int-array)
    (run-vtest-ge?-test long-array)
    (run-vtest-ge?-test float-array)
    (run-vtest-ge?-test double-array)))

(defn run-vtest-ge?-2d-tests
  [array-ctor]
  (let [sample1-2d [[-10 0 10]
                    [-20 0 20]
                    [-30 0 30]]
        sample2-2d [[-10 0 10]
                    [-21 0 21]
                    [100 1 10]]
        bool-2d [[true true true]
                 [true true false]
                 [false false true]]]
    (tu/equal-2d-arrays?
     (u/vec->array boolean-array bool-2d)
     (vtest-ge? (u/vec->array array-ctor sample1-2d)
                (u/vec->array array-ctor sample2-2d)))))

(deftest vtest-ge?-2d-test
  (testing "vtest-ge? 2d"
    (run-vtest-ge?-2d-tests byte-array)
    (run-vtest-ge?-2d-tests short-array)
    (run-vtest-ge?-2d-tests int-array)
    (run-vtest-ge?-2d-tests long-array)
    (run-vtest-ge?-2d-tests float-array)
    (run-vtest-ge?-2d-tests double-array)))

