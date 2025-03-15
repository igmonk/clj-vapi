(ns clj-vapi.core
  (:require [clj-vapi.protocols :as p]
            [clj-vapi.1d :refer :all]
            [clj-vapi.md :refer :all]))

;; ------------------ ARITH ------------------

(defn vsum [x] (p/vsum x))
(defn vprod [x] (p/vprod x))

(defn vadd [x1 x2 & xs]
  (reduce vadd (p/vadd x1 x2) xs))

(defn vmul [x1 x2 & xs]
  (reduce vmul (p/vmul x1 x2) xs))

(defn vabs [x] (p/vabs x))
(defn vneg [x] (p/vneg x))

(defn vexp [x] (p/vexp x))
(defn vlog [x] (p/vlog x))
(defn vlog10 [x] (p/vlog10 x))
(defn vlog1p [x] (p/vlog1p x))

(defn vsqrt [x] (p/vsqrt x))
(defn vcbrt [x] (p/vcbrt x))
(defn vpow [x e] (p/vpow x e))

(defn vscale [x s] (p/vscale x s))
(defn vfma [x1 x2 x3] (p/vfma x1 x2 x3))

(defn vmin [x] (p/vmin x))
(defn vmax [x] (p/vmax x))

;; ------------------ TRIG ------------------

(defn vcos [x] (p/vcos x))
(defn vsin [x] (p/vsin x))
(defn vtan [x] (p/vtan x))
(defn vacos [x] (p/vacos x))
(defn vasin [x] (p/vasin x))
(defn vatan [x] (p/vatan x))
(defn vhypot [x1 x2] (p/vhypot x1 x2))

;; ------------------ Comparison & Test FNs ------------------

(defn vtest-neg? [x] (p/vtest-neg? x))
(defn vtest-eq? [x1 x2] (p/vtest-eq? x1 x2))
(defn vtest-ne? [x1 x2] (p/vtest-ne? x1 x2))
(defn vtest-lt? [x1 x2] (p/vtest-lt? x1 x2))
(defn vtest-le? [x1 x2] (p/vtest-le? x1 x2))
(defn vtest-gt? [x1 x2] (p/vtest-gt? x1 x2))
(defn vtest-ge? [x1 x2] (p/vtest-ge? x1 x2))

