(ns clj-vapi.vtest-lt-test
  (:require [clj-vapi.core :refer [vtest-lt?]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.test :refer [deftest testing]]))

(def range-1-11 (range 1 11))
(def range-2-12 (range 2 12))

(def range-10-true (take 10 (repeat true)))
(def range-10-false (take 10 (repeat false)))
(def true-false-5x
  (mapcat identity (take 5 (repeat [true false]))))

(defn run-vtest-lt?-test
  [array-ctor]
  (tu/equal-arrays? (boolean-array range-10-false)
                    (vtest-lt? (array-ctor range-1-11)
                               (array-ctor range-1-11)))
  (tu/equal-arrays? (boolean-array range-10-true)
                    (vtest-lt? (array-ctor range-1-11)
                               (array-ctor range-2-12)))
  (tu/equal-arrays? (boolean-array range-10-false)
                    (vtest-lt? (array-ctor range-2-12)
                               (array-ctor range-1-11)))
  (tu/equal-arrays? (boolean-array true-false-5x)
                    (vtest-lt? (array-ctor range-1-11)
                               (array-ctor
                                (tu/pairwise-inc-keep range-1-11))))
  (tu/equal-arrays? (boolean-array true-false-5x)
                    (vtest-lt? (array-ctor range-1-11)
                               (array-ctor
                                (tu/pairwise-inc-dec range-1-11)))))

(deftest vtest-lt?-test
  (testing "vtest-lt?"
    (run-vtest-lt?-test byte-array)
    (run-vtest-lt?-test short-array)
    (run-vtest-lt?-test int-array)
    (run-vtest-lt?-test long-array)
    (run-vtest-lt?-test float-array)
    (run-vtest-lt?-test double-array)))

(defn run-vtest-lt?-2d-tests
  [array-ctor]
  (let [sample1-2d [[-10 0 10]
                    [-20 0 20]
                    [-30 0 30]]
        sample2-2d [[-10 0 10]
                    [-21 0 21]
                    [100 1 10]]
        bool-2d [[false false false]
                 [false false true]
                 [true true false]]]
    (tu/equal-2d-arrays?
     (u/vec->array boolean-array bool-2d)
     (vtest-lt? (u/vec->array array-ctor sample1-2d)
                (u/vec->array array-ctor sample2-2d)))))

(deftest vtest-lt?-2d-test
  (testing "vtest-lt? 2d"
    (run-vtest-lt?-2d-tests byte-array)
    (run-vtest-lt?-2d-tests short-array)
    (run-vtest-lt?-2d-tests int-array)
    (run-vtest-lt?-2d-tests long-array)
    (run-vtest-lt?-2d-tests float-array)
    (run-vtest-lt?-2d-tests double-array)))

