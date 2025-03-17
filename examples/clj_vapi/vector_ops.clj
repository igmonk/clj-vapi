(ns clj-vapi.vector-ops
  (:import (jdk.incubator.vector FloatVector
                                 VectorOperators))
  (:require [criterium.core :as c]
            [clj-vapi.macros :refer :all]
            [clj-vapi.core :refer [vsum vmul]]))

;; ------------------ Dot Product (option 1) ------------------

(defn vdot*
  "Returns the dot product of two vectors represented by 1d arrays.
  The function is a composition of high-level functions vmul and vsum
  defined on 1d arrays."
  [a1 a2]
  (vsum (vmul a1 a2)))

;; ------------------ Dot Product (option 2) ------------------

(defn dot*-reducer
  "Returns the sum of acc and the product of two scalars."
  [acc x1 x2]
  (+ acc (* x1 x2)))

(defmacro vreduce-dot*
  "Returns the dot product of two vectors represented by 1d arrays.
  Uses the low-level vreduce macro and parameterizes it with both
  scalar and vector reducers.
  Both reducers are compositions of the * and + operations."
  [v-type a1 a2]
  `(vreduce ~v-type dot*-reducer
       (.reduceLanes (.mul v1# v2#) VectorOperators/ADD)
       idx# ret# 0
       ~a1 v1# ~a2 v2#))

;; ------------------ Vector Ops Protocol ------------------

(defprotocol VectorOps
  (vdot1* [a1 a2])
  (vdot2* [a1 a2]))

(extend-protocol VectorOps
  float/1
  (vdot1* [a1 a2] (vdot* a1 a2))
  (vdot2* [a1 a2] (vreduce-dot* FloatVector a1 a2)))

;; ------------------ Usage ------------------

(def f1 (float-array [1 2 3 4 5]))
(def f2 (float-array [1 2 3 4 5]))

(vdot1* f1 f2) ;; => 55.0
(vdot2* f1 f2) ;; => 55.0

;; ------------------ Benchmarks ------------------

(def floats-1K (float-array 1000))

(c/quick-bench (vdot1* floats-1K floats-1K))
(c/quick-bench (vdot2* floats-1K floats-1K)) ;; ~2x faster than vdot1*

