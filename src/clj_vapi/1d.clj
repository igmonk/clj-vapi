(ns clj-vapi.1d
  (:import (jdk.incubator.vector ByteVector
                                 ShortVector
                                 IntVector
                                 LongVector
                                 FloatVector
                                 DoubleVector
                                 VectorOperators))
  (:require [clj-vapi.macros :refer [lanewise lanewise-bc lanewise-test vreduce-lanes]]
            [clj-vapi.protocols :as p]
            [clojure.math :as math]))

;; ------------------ ARITH ------------------

;; ------------------ vsum ------------------

(defmacro vreduce-sum
  [v-type a]
  `(vreduce-lanes ~v-type + VectorOperators/ADD 0 ~a))

(extend-protocol p/VSum
  byte/1 (vsum [a] (vreduce-sum ByteVector a))
  int/1 (vsum [a] (vreduce-sum IntVector a))
  long/1 (vsum [a] (vreduce-sum LongVector a))
  float/1 (vsum [a] (vreduce-sum FloatVector a))
  double/1 (vsum [a] (vreduce-sum DoubleVector a)))

;; ------------------ vprod ------------------

(defmacro vreduce-prod
  [v-type a]
  `(vreduce-lanes ~v-type * VectorOperators/MUL 1 ~a))

(extend-protocol p/VProd
  byte/1 (vprod [a] (vreduce-prod ByteVector a))
  int/1 (vprod [a] (vreduce-prod IntVector a))
  long/1 (vprod [a] (vreduce-prod LongVector a))
  float/1 (vprod [a] (vreduce-prod FloatVector a))
  double/1 (vprod [a] (vreduce-prod DoubleVector a)))

;; ------------------ vadd ------------------

(defmacro vmap-add
  [v-type a1 a2]
  `(lanewise ~v-type + VectorOperators/ADD ~a1 ~a2))

(extend-protocol p/VAdd
  byte/1 (vadd [a1 a2] (vmap-add ByteVector a1 a2))
  short/1 (vadd [a1 a2] (vmap-add ShortVector a1 a2))
  int/1 (vadd [a1 a2] (vmap-add IntVector a1 a2))
  long/1 (vadd [a1 a2] (vmap-add LongVector a1 a2))
  float/1 (vadd [a1 a2] (vmap-add FloatVector a1 a2))
  double/1 (vadd [a1 a2] (vmap-add DoubleVector a1 a2)))

;; ------------------ vmul ------------------

(defmacro vmap-mul
  [v-type a1 a2]
  `(lanewise ~v-type * VectorOperators/MUL ~a1 ~a2))

(extend-protocol p/VMul
  byte/1 (vmul [a1 a2] (vmap-mul ByteVector a1 a2))
  short/1 (vmul [a1 a2] (vmap-mul ShortVector a1 a2))
  int/1 (vmul [a1 a2] (vmap-mul IntVector a1 a2))
  long/1 (vmul [a1 a2] (vmap-mul LongVector a1 a2))
  float/1 (vmul [a1 a2] (vmap-mul FloatVector a1 a2))
  double/1 (vmul [a1 a2] (vmap-mul DoubleVector a1 a2)))

;; ------------------ vabs ------------------

(defmacro vmap-abs
  [v-type a]
  `(lanewise ~v-type abs VectorOperators/ABS ~a))

(extend-protocol p/VAbs
  byte/1 (vabs [a] (vmap-abs ByteVector a))
  short/1 (vabs [a] (vmap-abs ShortVector a))
  int/1 (vabs [a] (vmap-abs IntVector a))
  long/1 (vabs [a] (vmap-abs LongVector a))
  float/1 (vabs [a] (vmap-abs FloatVector a))
  double/1 (vabs [a] (vmap-abs DoubleVector a)))

;; ------------------ vneg ------------------

(defmacro vmap-neg
  [v-type a]
  `(lanewise ~v-type - VectorOperators/NEG ~a))

(extend-protocol p/VNeg
  byte/1 (vneg [a] (vmap-neg ByteVector a))
  short/1 (vneg [a] (vmap-neg ShortVector a))
  int/1 (vneg [a] (vmap-neg IntVector a))
  long/1 (vneg [a] (vmap-neg LongVector a))
  float/1 (vneg [a] (vmap-neg FloatVector a))
  double/1 (vneg [a] (vmap-neg DoubleVector a)))

;; ------------------ vexp ------------------

(defmacro vmap-exp
  [v-type a]
  `(lanewise ~v-type math/exp VectorOperators/EXP ~a))

(extend-protocol p/VExp
  float/1 (vexp [a] (vmap-exp FloatVector a))
  double/1 (vexp [a] (vmap-exp DoubleVector a)))

;; ------------------ vexpm1 ------------------

(defmacro vmap-expm1
  [v-type a]
  `(lanewise ~v-type math/expm1 VectorOperators/EXPM1 ~a))

(extend-protocol p/VExpm1
  float/1 (vexpm1 [a] (vmap-expm1 FloatVector a))
  double/1 (vexpm1 [a] (vmap-expm1 DoubleVector a)))

;; ------------------ vlog ------------------

(defmacro vmap-log
  [v-type a]
  `(lanewise ~v-type math/log VectorOperators/LOG ~a))

(extend-protocol p/VLog
  float/1 (vlog [a] (vmap-log FloatVector a))
  double/1 (vlog [a] (vmap-log DoubleVector a)))

;; ------------------ vlog10 ------------------

(defmacro vmap-log10
  [v-type a]
  `(lanewise ~v-type math/log10 VectorOperators/LOG10 ~a))

(extend-protocol p/VLog10
  float/1 (vlog10 [a] (vmap-log10 FloatVector a))
  double/1 (vlog10 [a] (vmap-log10 DoubleVector a)))

;; ------------------ vlog1p ------------------

(defmacro vmap-log1p
  [v-type a]
  `(lanewise ~v-type math/log1p VectorOperators/LOG1P ~a))

(extend-protocol p/VLog1P
  float/1 (vlog1p [a] (vmap-log1p FloatVector a))
  double/1 (vlog1p [a] (vmap-log1p DoubleVector a)))

;; ------------------ vsqrt ------------------

(defmacro vmap-sqrt
  [v-type a]
  `(lanewise ~v-type math/sqrt VectorOperators/SQRT ~a))

(extend-protocol p/VSqrt
  float/1 (vsqrt [a] (vmap-sqrt FloatVector a))
  double/1 (vsqrt [a] (vmap-sqrt DoubleVector a)))

;; ------------------ vcbrt ------------------

(defmacro vmap-cbrt
  [v-type a]
  `(lanewise ~v-type math/cbrt VectorOperators/CBRT ~a))

(extend-protocol p/VCbrt
  float/1 (vcbrt [a] (vmap-cbrt FloatVector a))
  double/1 (vcbrt [a] (vmap-cbrt DoubleVector a)))

;; ------------------ vpow ------------------

(defmacro vmap-pow
  [v-type a e]
  `(lanewise-bc ~v-type math/pow VectorOperators/POW ~a ~e))

(extend-protocol p/VPow
  float/1 (vpow [a e] (vmap-pow FloatVector a e))
  double/1 (vpow [a e] (vmap-pow DoubleVector a e)))

;; ------------------ vscale ------------------

(defmacro vmap-scale
  [v-type a s]
  `(lanewise-bc ~v-type * VectorOperators/MUL ~a ~s))

(extend-protocol p/VScale
  byte/1 (vscale [a s] (vmap-scale ByteVector a s))
  short/1 (vscale [a s] (vmap-scale ShortVector a s))
  int/1 (vscale [a s] (vmap-scale IntVector a s))
  long/1 (vscale [a s] (vmap-scale LongVector a s))
  float/1 (vscale [a s] (vmap-scale FloatVector a s))
  double/1 (vscale [a s] (vmap-scale DoubleVector a s)))

;; ------------------ vfma ------------------

(defmacro vmap-fma
  [v-type a1 a2 a3]
  `(lanewise ~v-type Math/fma VectorOperators/FMA ~a1 ~a2 ~a3))

(extend-protocol p/VFMA
  float/1 (vfma [a1 a2 a3] (vmap-fma FloatVector a1 a2 a3))
  double/1 (vfma [a1 a2 a3] (vmap-fma DoubleVector a1 a2 a3)))

;; ------------------ vmin ------------------

(defmacro vreduce-min
  [v-type a]
  `(when (pos? (count ~a))
     (vreduce-lanes ~v-type min VectorOperators/MIN (first ~a) ~a)))

(extend-protocol p/VMin
  byte/1 (vmin [a] (vreduce-min ByteVector a))
  short/1 (vmin [a] (vreduce-min ShortVector a))
  int/1 (vmin [a] (vreduce-min IntVector a))
  long/1 (vmin [a] (vreduce-min LongVector a))
  float/1 (vmin [a] (vreduce-min FloatVector a))
  double/1 (vmin [a] (vreduce-min DoubleVector a)))

;; ------------------ vmax ------------------

(defmacro vreduce-max
  [v-type a]
  `(when (pos? (count ~a))
     (vreduce-lanes ~v-type max VectorOperators/MAX (first ~a) ~a)))

(extend-protocol p/VMax
  byte/1 (vmax [a] (vreduce-max ByteVector a))
  short/1 (vmax [a] (vreduce-max ShortVector a))
  int/1 (vmax [a] (vreduce-max IntVector a))
  long/1 (vmax [a] (vreduce-max LongVector a))
  float/1 (vmax [a] (vreduce-max FloatVector a))
  double/1 (vmax [a] (vreduce-max DoubleVector a)))

;; ------------------ TRIG ------------------

;; ------------------ vcos ------------------

(defmacro vmap-cos
  [v-type a]
  `(lanewise ~v-type math/cos VectorOperators/COS ~a))

(extend-protocol p/VCos
  float/1 (vcos [a] (vmap-cos FloatVector a))
  double/1 (vcos [a] (vmap-cos DoubleVector a)))

;; ------------------ vsin ------------------

(defmacro vmap-sin
  [v-type a]
  `(lanewise ~v-type math/sin VectorOperators/SIN ~a))

(extend-protocol p/VSin
  float/1 (vsin [a] (vmap-sin FloatVector a))
  double/1 (vsin [a] (vmap-sin DoubleVector a)))

;; ------------------ vtan ------------------

(defmacro vmap-tan
  [v-type a]
  `(lanewise ~v-type math/tan VectorOperators/TAN ~a))

(extend-protocol p/VTan
  float/1 (vtan [a] (vmap-tan FloatVector a))
  double/1 (vtan [a] (vmap-tan DoubleVector a)))

;; ------------------ vacos ------------------

(defmacro vmap-acos
  [v-type a]
  `(lanewise ~v-type math/acos VectorOperators/ACOS ~a))

(extend-protocol p/VAcos
  float/1 (vacos [a] (vmap-acos FloatVector a))
  double/1 (vacos [a] (vmap-acos DoubleVector a)))

;; ------------------ vasin ------------------

(defmacro vmap-asin
  [v-type a]
  `(lanewise ~v-type math/asin VectorOperators/ASIN ~a))

(extend-protocol p/VAsin
  float/1 (vasin [a] (vmap-asin FloatVector a))
  double/1 (vasin [a] (vmap-asin DoubleVector a)))

;; ------------------ vatan ------------------

(defmacro vmap-atan
  [v-type a]
  `(lanewise ~v-type math/atan VectorOperators/ATAN ~a))

(extend-protocol p/VAtan
  float/1 (vatan [a] (vmap-atan FloatVector a))
  double/1 (vatan [a] (vmap-atan DoubleVector a)))

;; ------------------ vhypot ------------------

(defmacro vmap-hypot
  [v-type a1 a2]
  `(lanewise ~v-type math/hypot VectorOperators/HYPOT ~a1 ~a2))

(extend-protocol p/VHypot
  float/1 (vhypot [a1 a2] (vmap-hypot FloatVector a1 a2))
  double/1 (vhypot [a1 a2] (vmap-hypot DoubleVector a1 a2)))

;; ------------------ vcosh ------------------

(defmacro vmap-cosh
  [v-type a]
  `(lanewise ~v-type math/cosh VectorOperators/COSH ~a))

(extend-protocol p/VCosh
  float/1 (vcosh [a] (vmap-cosh FloatVector a))
  double/1 (vcosh [a] (vmap-cosh DoubleVector a)))

;; ------------------ vsinh ------------------

(defmacro vmap-sinh
  [v-type a]
  `(lanewise ~v-type math/sinh VectorOperators/SINH ~a))

(extend-protocol p/VSinh
  float/1 (vsinh [a] (vmap-sinh FloatVector a))
  double/1 (vsinh [a] (vmap-sinh DoubleVector a)))

;; ------------------ vtanh ------------------

(defmacro vmap-tanh
  [v-type a]
  `(lanewise ~v-type math/tanh VectorOperators/TANH ~a))

(extend-protocol p/VTanh
  float/1 (vtanh [a] (vmap-tanh FloatVector a))
  double/1 (vtanh [a] (vmap-tanh DoubleVector a)))

;; ------------------ Comparison & Test FNs ------------------

;; ------------------ vtest-neg? ------------------

(defmacro lanewise-test-neg?
  [v-type a]
  `(lanewise-test ~v-type neg? VectorOperators/IS_NEGATIVE ~a))

(extend-protocol p/VTestNeg
  byte/1 (vtest-neg? [a] (lanewise-test-neg? ByteVector a))
  short/1 (vtest-neg? [a] (lanewise-test-neg? ShortVector a))
  int/1 (vtest-neg? [a] (lanewise-test-neg? IntVector a))
  long/1 (vtest-neg? [a] (lanewise-test-neg? LongVector a))
  float/1 (vtest-neg? [a] (lanewise-test-neg? FloatVector a))
  double/1 (vtest-neg? [a] (lanewise-test-neg? DoubleVector a)))

;; ------------------ vtest-eq? ------------------

(defmacro lanewise-test-eq?
  [v-type a1 a2]
  `(lanewise-test ~v-type = VectorOperators/EQ ~a1 ~a2))

(extend-protocol p/VTestEQ
  byte/1 (vtest-eq? [a1 a2] (lanewise-test-eq? ByteVector a1 a2))
  short/1 (vtest-eq? [a1 a2] (lanewise-test-eq? ShortVector a1 a2))
  int/1 (vtest-eq? [a1 a2] (lanewise-test-eq? IntVector a1 a2))
  long/1 (vtest-eq? [a1 a2] (lanewise-test-eq? LongVector a1 a2))
  float/1 (vtest-eq? [a1 a2] (lanewise-test-eq? FloatVector a1 a2))
  double/1 (vtest-eq? [a1 a2] (lanewise-test-eq? DoubleVector a1 a2)))

;; ------------------ vtest-ne? ------------------

(defmacro lanewise-test-ne?
  [v-type a1 a2]
  `(lanewise-test ~v-type not= VectorOperators/NE ~a1 ~a2))

(extend-protocol p/VTestNE
  byte/1 (vtest-ne? [a1 a2] (lanewise-test-ne? ByteVector a1 a2))
  short/1 (vtest-ne? [a1 a2] (lanewise-test-ne? ShortVector a1 a2))
  int/1 (vtest-ne? [a1 a2] (lanewise-test-ne? IntVector a1 a2))
  long/1 (vtest-ne? [a1 a2] (lanewise-test-ne? LongVector a1 a2))
  float/1 (vtest-ne? [a1 a2] (lanewise-test-ne? FloatVector a1 a2))
  double/1 (vtest-ne? [a1 a2] (lanewise-test-ne? DoubleVector a1 a2)))

;; ------------------ vtest-lt? ------------------

(defmacro lanewise-test-lt?
  [v-type a1 a2]
  `(lanewise-test ~v-type < VectorOperators/LT ~a1 ~a2))

(extend-protocol p/VTestLT
  byte/1 (vtest-lt? [a1 a2] (lanewise-test-lt? ByteVector a1 a2))
  short/1 (vtest-lt? [a1 a2] (lanewise-test-lt? ShortVector a1 a2))
  int/1 (vtest-lt? [a1 a2] (lanewise-test-lt? IntVector a1 a2))
  long/1 (vtest-lt? [a1 a2] (lanewise-test-lt? LongVector a1 a2))
  float/1 (vtest-lt? [a1 a2] (lanewise-test-lt? FloatVector a1 a2))
  double/1 (vtest-lt? [a1 a2] (lanewise-test-lt? DoubleVector a1 a2)))

;; ------------------ vtest-le? ------------------

(defmacro lanewise-test-le?
  [v-type a1 a2]
  `(lanewise-test ~v-type <= VectorOperators/LE ~a1 ~a2))

(extend-protocol p/VTestLE
  byte/1 (vtest-le? [a1 a2] (lanewise-test-le? ByteVector a1 a2))
  short/1 (vtest-le? [a1 a2] (lanewise-test-le? ShortVector a1 a2))
  int/1 (vtest-le? [a1 a2] (lanewise-test-le? IntVector a1 a2))
  long/1 (vtest-le? [a1 a2] (lanewise-test-le? LongVector a1 a2))
  float/1 (vtest-le? [a1 a2] (lanewise-test-le? FloatVector a1 a2))
  double/1 (vtest-le? [a1 a2] (lanewise-test-le? DoubleVector a1 a2)))

;; ------------------ vtest-gt? ------------------

(defmacro lanewise-test-gt?
  [v-type a1 a2]
  `(lanewise-test ~v-type > VectorOperators/GT ~a1 ~a2))

(extend-protocol p/VTestGT
  byte/1 (vtest-gt? [a1 a2] (lanewise-test-gt? ByteVector a1 a2))
  short/1 (vtest-gt? [a1 a2] (lanewise-test-gt? ShortVector a1 a2))
  int/1 (vtest-gt? [a1 a2] (lanewise-test-gt? IntVector a1 a2))
  long/1 (vtest-gt? [a1 a2] (lanewise-test-gt? LongVector a1 a2))
  float/1 (vtest-gt? [a1 a2] (lanewise-test-gt? FloatVector a1 a2))
  double/1 (vtest-gt? [a1 a2] (lanewise-test-gt? DoubleVector a1 a2)))

;; ------------------ vtest-ge? ------------------

(defmacro lanewise-test-ge?
  [v-type a1 a2]
  `(lanewise-test ~v-type >= VectorOperators/GE ~a1 ~a2))

(extend-protocol p/VTestGE
  byte/1 (vtest-ge? [a1 a2] (lanewise-test-ge? ByteVector a1 a2))
  short/1 (vtest-ge? [a1 a2] (lanewise-test-ge? ShortVector a1 a2))
  int/1 (vtest-ge? [a1 a2] (lanewise-test-ge? IntVector a1 a2))
  long/1 (vtest-ge? [a1 a2] (lanewise-test-ge? LongVector a1 a2))
  float/1 (vtest-ge? [a1 a2] (lanewise-test-ge? FloatVector a1 a2))
  double/1 (vtest-ge? [a1 a2] (lanewise-test-ge? DoubleVector a1 a2)))

;; ------------------ Bitwise Op FNs ------------------

(defmacro lanewise-bit-and
  [v-type a1 a2]
  `(lanewise ~v-type bit-and VectorOperators/AND ~a1 ~a2))

(extend-protocol p/VBitAnd
  byte/1 (vbit-and [a1 a2] (lanewise-bit-and ByteVector a1 a2))
  short/1 (vbit-and [a1 a2] (lanewise-bit-and ShortVector a1 a2))
  int/1 (vbit-and [a1 a2] (lanewise-bit-and IntVector a1 a2))
  long/1 (vbit-and [a1 a2] (lanewise-bit-and LongVector a1 a2)))

(defmacro lanewise-bit-or
  [v-type a1 a2]
  `(lanewise ~v-type bit-or VectorOperators/OR ~a1 ~a2))

(extend-protocol p/VBitOr
  byte/1 (vbit-or [a1 a2] (lanewise-bit-or ByteVector a1 a2))
  short/1 (vbit-or [a1 a2] (lanewise-bit-or ShortVector a1 a2))
  int/1 (vbit-or [a1 a2] (lanewise-bit-or IntVector a1 a2))
  long/1 (vbit-or [a1 a2] (lanewise-bit-or LongVector a1 a2)))

(defmacro lanewise-bit-xor
  [v-type a1 a2]
  `(lanewise ~v-type bit-xor VectorOperators/XOR ~a1 ~a2))

(extend-protocol p/VBitXor
  byte/1 (vbit-xor [a1 a2] (lanewise-bit-xor ByteVector a1 a2))
  short/1 (vbit-xor [a1 a2] (lanewise-bit-xor ShortVector a1 a2))
  int/1 (vbit-xor [a1 a2] (lanewise-bit-xor IntVector a1 a2))
  long/1 (vbit-xor [a1 a2] (lanewise-bit-xor LongVector a1 a2)))

(defmacro lanewise-bit-not
  [v-type a]
  `(lanewise ~v-type bit-not VectorOperators/NOT ~a))

(extend-protocol p/VBitNot
  byte/1 (vbit-not [a] (lanewise-bit-not ByteVector a))
  short/1 (vbit-not [a] (lanewise-bit-not ShortVector a))
  int/1 (vbit-not [a] (lanewise-bit-not IntVector a))
  long/1 (vbit-not [a] (lanewise-bit-not LongVector a)))

(defmacro lanewise-bit-and-not
  [v-type a1 a2]
  `(lanewise ~v-type bit-and-not VectorOperators/AND_NOT ~a1 ~a2))

(extend-protocol p/VBitAndNot
  byte/1 (vbit-and-not [a1 a2] (lanewise-bit-and-not ByteVector a1 a2))
  short/1 (vbit-and-not [a1 a2] (lanewise-bit-and-not ShortVector a1 a2))
  int/1 (vbit-and-not [a1 a2] (lanewise-bit-and-not IntVector a1 a2))
  long/1 (vbit-and-not [a1 a2] (lanewise-bit-and-not LongVector a1 a2)))

;; ------------------ MISC ------------------
(defmacro lanewise-zomo
  [v-type a]
  `(lanewise ~v-type #(if (= % 0) 0 -1) VectorOperators/ZOMO ~a))

(extend-protocol p/VZomo
  byte/1 (vzomo [a] (lanewise-zomo ByteVector a))
  short/1 (vzomo [a] (lanewise-zomo ShortVector a))
  int/1 (vzomo [a] (lanewise-zomo IntVector a))
  long/1 (vzomo [a] (lanewise-zomo LongVector a)))

