(ns clj-vapi.vsum-bench
  (:require [criterium.core :as c]
            [clj-vapi.core :refer [vsum]]))

(def range-1K (range 1 1001))
(def range-1M (range 1 1000001))

;; 1d ints 1K
(def ints-1K (int-array range-1K))
(c/quick-bench (vsum ints-1K))

;; 1d ints 1M
(def ints-1M (int-array range-1M))
(c/quick-bench (vsum ints-1M))

;; 1d floats 1K
(def floats-1K (float-array range-1K))
(c/quick-bench (vsum floats-1K))

;; 1d floats 1M
(def floats-1M (float-array range-1M))
(c/quick-bench (vsum floats-1M))

;; 2d ints 1000 x 1000
(def ints-2d (make-array Integer/TYPE 1000 1000))
(c/quick-bench (vsum ints-2d))

;; 2d floats 1000 x 1000
(def floats-2d (make-array Float/TYPE 1000 1000))
(c/quick-bench (vsum floats-2d))

