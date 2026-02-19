(ns clj-vapi.vfma-test
  (:require [clj-vapi.core :refer [vfma]]
            [clj-vapi.test-utils :as tu]
            [clojure.math :as math]
            [clojure.test :refer [deftest testing]]))

(def epsilon (math/pow 10 -6))

(def range-1-11 (map float (range 1 11)))
(def range-2-12 (map float (range 2 12)))
(def range-3-13 (map float (range 3 13)))

(def range-fma (map #(Math/fma %1 %2 %3)
                    range-1-11 range-2-12 range-3-13))

(defn run-floating-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor range-fma)
   (vfma (array-ctor range-1-11)
         (array-ctor range-2-12)
         (array-ctor range-3-13))))

(deftest vadd-test
  (testing "vadd floating"
    (run-floating-tests float-array)
    (run-floating-tests double-array)))

