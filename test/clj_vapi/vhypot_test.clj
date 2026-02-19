(ns clj-vapi.vhypot-test
  (:require [clj-vapi.core :refer [vhypot]]
            [clj-vapi.test-utils :as tu]
            [clojure.math :as math]
            [clojure.test :refer [deftest testing]]))

(def epsilon (math/pow 10 -6))

(def pyth-triples
  [[3 4 5]
   [5 12 13]
   [8 15 17]
   [7 24 25]
   [20 21 29]
   [12 35 37]
   [9 40 41]])

(def sides1 (map first pyth-triples))
(def sides2 (map second pyth-triples))
(def hypots (map #(nth % 2) pyth-triples))

(defn run-vhypot-tests
  [array-ctor]
  (tu/equalish-arrays?
   epsilon
   (array-ctor hypots)
   (vhypot (array-ctor sides1)
           (array-ctor sides2))))

(deftest vhypot-test
  (testing "vhypot"
    (run-vhypot-tests float-array)
    (run-vhypot-tests double-array)))

