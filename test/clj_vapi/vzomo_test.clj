(ns clj-vapi.vzomo-test
  (:require [clojure.test :refer :all]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vzomo]]))

(def args [-3 0 3 -2 0 2 -1 0 1])
(def result (map #(if (= % 0) 0 -1) args))

(defn run-integral-tests
  [array-ctor]
  (tu/equal-arrays?
   (array-ctor result)
   (vzomo (array-ctor args))))

(deftest vzomo-test
  (testing "vzomo integral"
    (run-integral-tests byte-array)
    (run-integral-tests short-array)
    (run-integral-tests int-array)
    (run-integral-tests long-array)))

(defn run-integral-2d-tests
  [array-ctor]
  (let [in-2d (vec (map vec (partition 3 args)))
        out-2d (vec (map vec (partition 3 result)))]
    (tu/equal-2d-arrays?
     (u/vec->array array-ctor out-2d)
     (vzomo (u/vec->array array-ctor in-2d)))))

(deftest vzomo-2d-test
  (testing "vzomo-2d integral"
    (run-integral-2d-tests byte-array)
    (run-integral-2d-tests short-array)
    (run-integral-2d-tests int-array)
    (run-integral-2d-tests long-array)))

