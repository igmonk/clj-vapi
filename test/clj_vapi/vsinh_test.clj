(ns clj-vapi.vsinh-test
  (:require [clojure.test :refer :all]
            [clojure.math :as math]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clj-vapi.core :refer [vsinh]]))

(def epsilon 1e-6)

(def args [-4 -3 -2 -1 0 1 2 3 4])
(def results (map math/sinh args))

(defn run-vsinh-tests
  [array-ctor]
  (tu/equalish-arrays?
     epsilon
     (array-ctor results)
     (vsinh (array-ctor args))))

(deftest vsinh-test
  (testing "vsinh"
    (run-vsinh-tests float-array)
    (run-vsinh-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (let [args-3-by-3 (vec (map vec (partition 3 args)))
        res-3-by-3 (vec (map vec (partition 3 results)))]
    (tu/equalish-2d-arrays?
     epsilon
     (vsinh (u/vec->array array-ctor args-3-by-3))
     (u/vec->array array-ctor res-3-by-3))))

(deftest vsinh-2d-test
  (testing "vsinh-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

