(ns clj-vapi.vtest-eq-test
  (:require [clojure.test :refer :all]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vtest-eq?]]))

(def range-1-11 (range 1 11))
(def range-2-12 (range 2 12))
(def odds-zeros [1 0 3 0 5 0 7 0 9 0])

(def range-10-true (take 10 (repeat true)))
(def range-10-false (take 10 (repeat false)))
(def true-false-5x
  (mapcat identity (take 5 (repeat [true false]))))

(defn run-vtest-eq?-test
  [array-ctor]
  (tu/equal-arrays? (boolean-array range-10-true)
                    (vtest-eq? (array-ctor range-1-11)
                               (array-ctor range-1-11)))
  (tu/equal-arrays? (boolean-array range-10-false)
                    (vtest-eq? (array-ctor range-1-11)
                               (array-ctor range-2-12)))
  (tu/equal-arrays? (boolean-array true-false-5x)
                    (vtest-eq? (array-ctor range-1-11)
                               (array-ctor odds-zeros))))

(deftest vtest-eq-test
  (testing "vtest-eq?"
    (run-vtest-eq?-test byte-array)
    (run-vtest-eq?-test short-array)
    (run-vtest-eq?-test int-array)
    (run-vtest-eq?-test long-array)
    (run-vtest-eq?-test float-array)
    (run-vtest-eq?-test double-array)))

(defn run-vtest-eq?-2d-tests
  [array-ctor]
  (let [sample1-2d [[-10 0 10]
                    [-20 0 20]
                    [-30 0 30]]
        sample2-2d [[-10 0 10]
                    [-21 0 21]
                    [100 1 10]]
        bool-2d [[true true true]
                 [false true false]
                 [false false false]]]
    (tu/equal-2d-arrays?
     (u/vec->array boolean-array bool-2d)
     (vtest-eq? (u/vec->array array-ctor sample1-2d)
                (u/vec->array array-ctor sample2-2d)))))

(deftest vtest-eq?-2d-test
  (testing "vtest-eq? 2d"
    (run-vtest-eq?-2d-tests byte-array)
    (run-vtest-eq?-2d-tests short-array)
    (run-vtest-eq?-2d-tests int-array)
    (run-vtest-eq?-2d-tests long-array)
    (run-vtest-eq?-2d-tests float-array)
    (run-vtest-eq?-2d-tests double-array)))

