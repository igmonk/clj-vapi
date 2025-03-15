(ns clj-vapi.vtest-le-test
  (:require [clojure.test :refer :all]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vtest-le?]]))

(def range-1-11 (range 1 11))
(def range-2-12 (range 2 12))

(def range-10-true (take 10 (repeat true)))
(def range-10-false (take 10 (repeat false)))
(def true-false-5x
  (mapcat identity (take 5 (repeat [true false]))))

(defn run-vtest-le?-test
  [array-ctor]
  (tu/equal-arrays? (boolean-array range-10-true)
                    (vtest-le? (array-ctor range-1-11)
                               (array-ctor range-1-11)))
  (tu/equal-arrays? (boolean-array range-10-true)
                    (vtest-le? (array-ctor range-1-11)
                               (array-ctor range-2-12)))
  (tu/equal-arrays? (boolean-array range-10-false)
                    (vtest-le? (array-ctor range-2-12)
                               (array-ctor range-1-11)))
  (tu/equal-arrays? (boolean-array range-10-true)
                    (vtest-le? (array-ctor range-1-11)
                               (array-ctor
                                (tu/pairwise-inc-keep range-1-11))))
  (tu/equal-arrays? (boolean-array true-false-5x)
                    (vtest-le? (array-ctor range-1-11)
                               (array-ctor
                                (tu/pairwise-inc-dec range-1-11)))))

(deftest vtest-le?-test
  (testing "vtest-le?"
    (run-vtest-le?-test byte-array)
    (run-vtest-le?-test short-array)
    (run-vtest-le?-test int-array)
    (run-vtest-le?-test long-array)
    (run-vtest-le?-test float-array)
    (run-vtest-le?-test double-array)))

(defn run-vtest-le?-2d-tests
  [array-ctor]
  (let [sample1-2d [[-10 0 10]
                    [-20 0 20]
                    [-30 0 30]]
        sample2-2d [[-10 0 10]
                    [-21 0 21]
                    [100 1 10]]
        bool-2d [[true true true]
                 [false true true]
                 [true true false]]]
    (tu/equal-2d-arrays?
     (u/vec->array boolean-array bool-2d)
     (vtest-le? (u/vec->array array-ctor sample1-2d)
                (u/vec->array array-ctor sample2-2d)))))

(deftest vtest-le?-2d-test
  (testing "vtest-le? 2d"
    (run-vtest-le?-2d-tests byte-array)
    (run-vtest-le?-2d-tests short-array)
    (run-vtest-le?-2d-tests int-array)
    (run-vtest-le?-2d-tests long-array)
    (run-vtest-le?-2d-tests float-array)
    (run-vtest-le?-2d-tests double-array)))

