(ns clj-vapi.vtest-gt-test
  (:require [clj-vapi.core :refer [vtest-gt?]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.test :refer [deftest testing]]))

(def range-1-11 (range 1 11))
(def range-2-12 (range 2 12))

(def range-10-true (take 10 (repeat true)))
(def range-10-false (take 10 (repeat false)))
(def false-true-5x
  (mapcat identity (take 5 (repeat [false true]))))

(defn run-vtest-gt?-test
  [array-ctor]
  (tu/equal-arrays? (boolean-array range-10-false)
                    (vtest-gt? (array-ctor range-1-11)
                               (array-ctor range-1-11)))
  (tu/equal-arrays? (boolean-array range-10-false)
                    (vtest-gt? (array-ctor range-1-11)
                               (array-ctor range-2-12)))
  (tu/equal-arrays? (boolean-array range-10-true)
                    (vtest-gt? (array-ctor range-2-12)
                               (array-ctor range-1-11)))
  (tu/equal-arrays? (boolean-array range-10-false)
                    (vtest-gt? (array-ctor range-1-11)
                               (array-ctor
                                (tu/pairwise-inc-keep range-1-11))))
  (tu/equal-arrays? (boolean-array false-true-5x)
                    (vtest-gt? (array-ctor range-1-11)
                               (array-ctor
                                (tu/pairwise-inc-dec range-1-11)))))

(deftest vtest-gt?-test
  (testing "vtest-gt?"
    (run-vtest-gt?-test byte-array)
    (run-vtest-gt?-test short-array)
    (run-vtest-gt?-test int-array)
    (run-vtest-gt?-test long-array)
    (run-vtest-gt?-test float-array)
    (run-vtest-gt?-test double-array)))

(defn run-vtest-gt?-2d-tests
  [array-ctor]
  (let [sample1-2d [[-10 0 10]
                    [-20 0 20]
                    [-30 0 30]]
        sample2-2d [[-10 0 10]
                    [-21 0 21]
                    [100 1 10]]
        bool-2d [[false false false]
                 [true false false]
                 [false false true]]]
    (tu/equal-2d-arrays?
     (u/vec->array boolean-array bool-2d)
     (vtest-gt? (u/vec->array array-ctor sample1-2d)
                (u/vec->array array-ctor sample2-2d)))))

(deftest vtest-gt?-2d-test
  (testing "vtest-gt? 2d"
    (run-vtest-gt?-2d-tests byte-array)
    (run-vtest-gt?-2d-tests short-array)
    (run-vtest-gt?-2d-tests int-array)
    (run-vtest-gt?-2d-tests long-array)
    (run-vtest-gt?-2d-tests float-array)
    (run-vtest-gt?-2d-tests double-array)))

