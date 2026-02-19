(ns clj-vapi.vbit-not-test
  (:require [clj-vapi.core :refer [vbit-not]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.test :refer [deftest testing]]))

(def bin-literals
  [2r0000 2r0001 2r0010 2r0011
   2r0100 2r0101 2r0110 2r0111
   2r1000 2r1001 2r1010 2r1011
   2r1100 2r1101 2r1110 2r1111])

(def bin-literals-not (map bit-not bin-literals))

(defn run-integral-tests
  [array-ctor]
  (tu/equal-arrays?
   (array-ctor bin-literals-not)
   (vbit-not (array-ctor bin-literals)))
  (tu/equal-arrays?
   (array-ctor bin-literals)
   (vbit-not (array-ctor bin-literals-not))))

(deftest vbit-not-test
  (testing "vbit-not integral"
    (run-integral-tests byte-array)
    (run-integral-tests short-array)
    (run-integral-tests int-array)
    (run-integral-tests long-array)))

(defn run-integral-2d-tests
  [array-ctor]
  (let [bit-lit-2d (vec (map vec (partition 4 bin-literals)))
        bit-lit-not-2d (vec (map vec (partition 4 bin-literals-not)))]
    (tu/equal-2d-arrays?
     (u/vec->array array-ctor bit-lit-not-2d)
     (vbit-not (u/vec->array array-ctor bit-lit-2d)))
    (tu/equal-2d-arrays?
     (u/vec->array array-ctor bit-lit-2d)
     (vbit-not (u/vec->array array-ctor bit-lit-not-2d)))))

(deftest vbit-not-2d-test
  (testing "vbit-not-2d integral"
    (run-integral-2d-tests byte-array)
    (run-integral-2d-tests short-array)
    (run-integral-2d-tests int-array)
    (run-integral-2d-tests long-array)))

