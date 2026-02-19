(ns clj-vapi.test-utils
  (:import (java.util Arrays))
  (:require [clojure.test :refer [is]]))

(defn equal-arrays?
  [a1 a2]
  (is (Arrays/equals a1 a2)))

(defn pairwise-apply
  [f1 f2 coll]
  (mapcat (fn [[x1 x2]] [(f1 x1) (f2 x2)])
          (partition 2 coll)))

(defn pairwise-inc-keep
  [coll]
  (pairwise-apply inc identity coll))

(defn pairwise-inc-dec
  [coll]
  (pairwise-apply inc dec coll))

;; Float numbers comparison
;; https://stackoverflow.com/questions/4915462/how-should-i-do-floating-point-comparison
;;
;; Modes:
;; - absolute
;; - relative
;; - mixed/adaptive

(defn close-to?
  [x y epsilon]
  (< (abs (- x y)) epsilon))

(defn equalish-arrays?
  [epsilon a1 a2]
  (letfn [(pred [[x y]]
            (close-to? x y epsilon))]
    (is (every? pred (map vector a1 a2)))))

(defn square [x] (* x x))

(defn cube [x] (* x x x))

;; Arrays FNs

(defn equal-2d-arrays?
  [a1 a2]
  (is (every? true? (map Arrays/equals a1 a2))))

(defn equalish-2d-arrays?
  [epsilon a1 a2]
  (is (every? true?
              (map #(equalish-arrays? epsilon %1 %2)
                   a1 a2))))

