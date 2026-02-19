(ns clj-vapi.vcosh-test
  (:require [clj-vapi.core :refer [vcosh]]
            [clj-vapi.test-utils :as tu]
            [clj-vapi.utils :as u]
            [clojure.math :as math]
            [clojure.test :refer [deftest testing]]))

(def epsilon 1e-6)

(def args [-4 -3 -2 -1 0 1 2 3 4])
(def results (map math/cosh args))

(defn run-vcosh-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor results)
   (vcosh (array-ctor args))))

(deftest vcosh-test
  (testing "vcosh"
    (run-vcosh-tests float-array)
    (run-vcosh-tests double-array)))

(defn run-floating-2d-tests
  [array-ctor]
  (let [args-3-by-3 (vec (map vec (partition 3 args)))
        res-3-by-3 (vec (map vec (partition 3 results)))]
    (tu/equalish-2d-arrays?
     epsilon
     (vcosh (u/vec->array array-ctor args-3-by-3))
     (u/vec->array array-ctor res-3-by-3))))

(deftest vcosh-2d-test
  (testing "vcosh-2d floating"
    (run-floating-2d-tests float-array)
    (run-floating-2d-tests double-array)))

