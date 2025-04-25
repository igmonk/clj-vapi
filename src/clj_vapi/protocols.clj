(ns clj-vapi.protocols)

;; ------------------ ARITH ------------------

(defprotocol VSum (vsum [a]))
(defprotocol VProd (vprod [a]))

(defprotocol VAdd (vadd [a1 a2]))
(defprotocol VMul (vmul [a1 a2]))

(defprotocol VAbs (vabs [a]))
(defprotocol VNeg (vneg [a]))

(defprotocol VExp (vexp [a]))
(defprotocol VExpm1 (vexpm1 [a]))
(defprotocol VLog (vlog [a]))
(defprotocol VLog10 (vlog10 [a]))
(defprotocol VLog1P (vlog1p [a]))

(defprotocol VSqrt (vsqrt [a]))
(defprotocol VCbrt (vcbrt [a]))
(defprotocol VPow (vpow [a e]))

(defprotocol VScale (vscale [a s]))
(defprotocol VFMA (vfma [a1 a2 a3]))

(defprotocol VMin (vmin [a]))
(defprotocol VMax (vmax [a]))

;; ------------------ TRIG ------------------

(defprotocol VCos (vcos [a]))
(defprotocol VSin (vsin [a]))
(defprotocol VTan (vtan [a]))
(defprotocol VAcos (vacos [a]))
(defprotocol VAsin (vasin [a]))
(defprotocol VAtan (vatan [a]))
(defprotocol VHypot (vhypot [a1 a2]))
(defprotocol VCosh (vcosh [a]))
(defprotocol VSinh (vsinh [a]))
(defprotocol VTanh (vtanh [a]))

;; ------------------ Comparison & Test FNs ------------------

(defprotocol VTestNeg (vtest-neg? [a]))
(defprotocol VTestEQ (vtest-eq? [a1 a2]))
(defprotocol VTestNE (vtest-ne? [a1 a2]))
(defprotocol VTestLT (vtest-lt? [a1 a2]))
(defprotocol VTestLE (vtest-le? [a1 a2]))
(defprotocol VTestGT (vtest-gt? [a1 a2]))
(defprotocol VTestGE (vtest-ge? [a1 a2]))

;; ------------------ Bitwise Op FNs ------------------

(defprotocol VBitAnd (vbit-and [a1 a2]))
(defprotocol VBitOr (vbit-or [a1 a2]))
(defprotocol VBitXor (vbit-xor [a1 a2]))
(defprotocol VBitNot (vbit-not [a]))
(defprotocol VBitAndNot (vbit-and-not [a1 a2]))

