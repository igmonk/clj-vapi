(ns clj-vapi.vbit-and-test
  (:require [clj-vapi.core :refer [vbit-and]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.test :refer [deftest testing]]))

(def in1 [2r0000 2r0001 2r0010 2r0100 2r1000 2r0011 2r0110 2r1100 2r1001])
(def in2 [2r1111 2r1111 2r1111 2r1111 2r1111 2r0111 2r0111 2r0111 2r0110])
(def out [2r0000 2r0001 2r0010 2r0100 2r1000 2r0011 2r0110 2r0100 2r0000])

(defn run-integral-tests
  [array-ctor]
  (tu/equal-arrays?
   (array-ctor out)
   (vbit-and (array-ctor in1)
             (array-ctor in2))))

(deftest vbit-and-test
  (testing "vbit-and integral"
    (run-integral-tests byte-array)
    (run-integral-tests short-array)
    (run-integral-tests int-array)
    (run-integral-tests long-array)))

(defn run-integral-2d-tests
  [array-ctor]
  (let [in1-2d (vec (map vec (partition 3 in1)))
        in2-2d (vec (map vec (partition 3 in2)))
        out-2d (vec (map vec (partition 3 out)))]
    (tu/equal-2d-arrays?
     (u/vec->array array-ctor out-2d)
     (vbit-and (u/vec->array array-ctor in1-2d)
               (u/vec->array array-ctor in2-2d)))))

(deftest vbit-and-2d-test
  (testing "vbit-and-2d integral"
    (run-integral-2d-tests byte-array)
    (run-integral-2d-tests short-array)
    (run-integral-2d-tests int-array)
    (run-integral-2d-tests long-array)))

