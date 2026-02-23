(ns clj-vapi.core
  (:require [clj-vapi.1d :refer :all]
            [clj-vapi.md :refer :all]
            [clj-vapi.protocols :as p]))

;; ============================================================
;; ARITHMETIC OPERATIONS
;; ============================================================

(defn vsum
  "Returns the sum of all elements in the vector x."
  [x]
  (p/vsum x))

(defn vprod
  "Returns the product of all elements in the vector x."
  [x]
  (p/vprod x))

(defn vadd
  "Returns a vector that represents the element-wise sum of
  the vectors x1, x2, and any additional vectors in xs."
  [x1 x2 & xs]
  (reduce vadd (p/vadd x1 x2) xs))

(defn vmul
  "Returns a vector that represents the element-wise product of
  the vectors x1, x2, and any additional vectors in xs."
  [x1 x2 & xs]
  (reduce vmul (p/vmul x1 x2) xs))

(defn vabs
  "Returns a vector containing the element-wise absolute values of x."
  [x]
  (p/vabs x))

(defn vneg
  "Returns a vector containing the element-wise negatives of x."
  [x]
  (p/vneg x))

(defn vexp
  "Returns a vector containing the element-wise exponentials of x."
  [x]
  (p/vexp x))

(defn vexpm1
  "Returns a vector containing the element-wise exp(x) - 1 of x."
  [x]
  (p/vexpm1 x))

(defn vlog
  "Returns a vector containing the element-wise natural logarithms of x."
  [x]
  (p/vlog x))

(defn vlog10
  "Returns a vector containing the element-wise base-10 logarithms of x."
  [x]
  (p/vlog10 x))

(defn vlog1p
  "Returns a vector containing the element-wise log(1 + x) of x."
  [x]
  (p/vlog1p x))

(defn vsqrt
  "Returns a vector containing the element-wise square roots of x."
  [x]
  (p/vsqrt x))

(defn vcbrt
  "Returns a vector containing the element-wise cube roots of x."
  [x]
  (p/vcbrt x))

(defn vpow
  "Returns a vector containing the element-wise powers of x raised to e."
  [x e]
  (p/vpow x e))

(defn vscale
  "Returns a vector containing the element-wise products of x and s."
  [x s]
  (p/vscale x s))

(defn vfma
  "Returns a vector containing the element-wise fused multiply-add of x1, x2, and x3."
  [x1 x2 x3]
  (p/vfma x1 x2 x3))

(defn vmin
  "Returns the minimum value in the vector x."
  [x]
  (p/vmin x))

(defn vmax
  "Returns the maximum value in the vector x."
  [x]
  (p/vmax x))

;; ============================================================
;; TRIGONOMETRIC OPERATIONS
;; ============================================================

(defn vcos
  "Returns a vector containing the element-wise cosines of x."
  [x]
  (p/vcos x))

(defn vsin
  "Returns a vector containing the element-wise sines of x."
  [x]
  (p/vsin x))

(defn vtan
  "Returns a vector containing the element-wise tangents of x."
  [x]
  (p/vtan x))

(defn vacos
  "Returns a vector containing the element-wise arc cosines of x."
  [x]
  (p/vacos x))

(defn vasin
  "Returns a vector containing the element-wise arc sines of x."
  [x]
  (p/vasin x))

(defn vatan
  "Returns a vector containing the element-wise arc tangents of x."
  [x]
  (p/vatan x))

(defn vhypot
  "Returns a vector containing the element-wise hypotenuses
  using catheti from x1 and x2."
  [x1 x2]
  (p/vhypot x1 x2))

(defn vcosh
  "Returns a vector containing the element-wise hyperbolic cosines of x."
  [x]
  (p/vcosh x))

(defn vsinh
  "Returns a vector containing the element-wise hyperbolic sines of x."
  [x]
  (p/vsinh x))

(defn vtanh
  "Returns a vector containing the element-wise hyperbolic tangents of x."
  [x]
  (p/vtanh x))

;; ============================================================
;; COMPARISON & TEST OPERATIONS
;; ============================================================

(defn vtest-neg?
  "Returns a vector containing the element-wise negative tests of x."
  [x]
  (p/vtest-neg? x))

(defn vtest-eq?
  "Returns a vector containing the element-wise equality tests between x1 and x2."
  [x1 x2]
  (p/vtest-eq? x1 x2))

(defn vtest-ne?
  "Returns a vector containing the element-wise inequality tests between x1 and x2."
  [x1 x2]
  (p/vtest-ne? x1 x2))

(defn vtest-lt?
  "Returns a vector containing the element-wise less-than tests between x1 and x2."
  [x1 x2]
  (p/vtest-lt? x1 x2))

(defn vtest-le?
  "Returns a vector containing the element-wise less-than-or-equal tests between x1 and x2."
  [x1 x2]
  (p/vtest-le? x1 x2))

(defn vtest-gt?
  "Returns a vector containing the element-wise greater-than tests between x1 and x2."
  [x1 x2]
  (p/vtest-gt? x1 x2))

(defn vtest-ge?
  "Returns a vector containing the element-wise greater-than-or-equal tests between x1 and x2."
  [x1 x2]
  (p/vtest-ge? x1 x2))

;; ============================================================
;; BITWISE OPERATIONS
;; ============================================================

(defn vbit-and
  "Returns a vector containing the element-wise bitwise ANDs between x1 and x2."
  [x1 x2]
  (p/vbit-and x1 x2))

(defn vbit-or
  "Returns a vector containing the element-wise bitwise ORs between x1 and x2."
  [x1 x2]
  (p/vbit-or x1 x2))

(defn vbit-xor
  "Returns a vector containing the element-wise bitwise XORs between x1 and x2."
  [x1 x2]
  (p/vbit-xor x1 x2))

(defn vbit-not
  "Returns a vector containing the element-wise bitwise NOTs of x."
  [x]
  (p/vbit-not x))

(defn vbit-and-not
  "Returns a vector containing the element-wise bitwise AND-NOTs between x1 and x2."
  [x1 x2]
  (p/vbit-and-not x1 x2))

;; ============================================================
;; MISC OPERATIONS
;; ============================================================

(defn vzomo
  "Returns a vector containing the element-wise zero-or-minus-one values of x."
  [x]
  (p/vzomo x))

