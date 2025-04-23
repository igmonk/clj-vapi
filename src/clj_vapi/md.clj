(ns clj-vapi.md
  (:require [clj-vapi.protocols :as p]
            [clj-vapi.1d :refer :all]))

(defn reduce-array
  [f init x]
  (areduce x i ret init
           (f ret (aget ^objects x i))))

(defn map-arrays
  ([f x]
   (amap ^objects x idx _
         (f (aget ^objects x idx))))
  ([f x1 x2]
   (amap ^objects x1 idx _
         (f (aget ^objects x1 idx)
            (aget ^objects x2 idx)))))

(defmacro with-type-check
  [ty method & body]
  `(if (.isArray ~ty)
     ~@body
     (throw
      (IllegalArgumentException.
       (format "No implementation of method :%s of protocol %s found for %s"
               (-> ~method var meta :name)
               (-> ~method var meta :protocol)
               ~ty)))))

(defmacro vreduce1-md
  [method f init x]
  `(with-type-check (type ~x) ~method
     (reduce-array ~f ~init ~x)))

(defmacro vmap1-md
  ([method f x]
   `(with-type-check (type ~x) ~method
      (map-arrays ~f ~x)))
  ([method f x1 x2]
   `(with-type-check (type ~x1) ~method
      (map-arrays ~f ~x1 ~x2))))

(defmacro vmap-md
  ([f x] `(vmap1-md ~f ~f ~x))
  ([f x1 x2] `(vmap1-md ~f ~f ~x1 ~x2)))

(defmacro vtest-md
  ([vtest-fn x]
   `(with-type-check (type ~x) ~vtest-fn
      (if (.isArray (type (first ~x)))
        (into-array (map ~vtest-fn ~x))
        (~vtest-fn ~x))))  
  ([vtest-fn x1 x2]
   `(with-type-check (type ~x1) ~vtest-fn
      (if (.isArray (type (first ~x1)))
        (into-array (map ~vtest-fn ~x1 ~x2))
        (~vtest-fn ~x1 ~x2)))))

(defn vsum-reducer [acc x] (+ acc (p/vsum x)))
(defn vprod-reducer [acc x] (* acc (p/vprod x)))

;; ------------------ java.lang.Object Extension ------------------

(extend-type java.lang.Object
  p/VSum (vsum [x] (vreduce1-md p/vsum vsum-reducer 0 x))
  p/VProd (vprod [x] (vreduce1-md p/vprod vprod-reducer 1 x))
  p/VAdd (vadd [x1 x2] (vmap-md p/vadd x1 x2))
  p/VMul (vmul [x1 x2] (vmap-md p/vmul x1 x2))
  p/VAbs (vabs [x] (vmap-md p/vabs x))
  p/VNeg (vneg [x] (vmap-md p/vneg x))
  p/VExp (vexp [x] (vmap-md p/vexp x))
  p/VLog (vlog [x] (vmap-md p/vlog x))
  p/VLog10 (vlog10 [x] (vmap-md p/vlog10 x))
  p/VLog1P (vlog1p [x] (vmap-md p/vlog1p x))
  p/VSqrt (vsqrt [x] (vmap-md p/vsqrt x))
  p/VCbrt (vcbrt [x] (vmap-md p/vcbrt x))
  p/VPow (vpow [x e] (vmap1-md p/vpow #(p/vpow % e) x))
  p/VScale (vscale [x s] (vmap1-md p/vscale #(p/vscale % s) x))
  p/VCos (vcos [x] (vmap-md p/vcos x))
  p/VSin (vsin [x] (vmap-md p/vsin x))
  p/VTan (vtan [x] (vmap-md p/vtan x))
  p/VAcos (vacos [x] (vmap-md p/vacos x))
  p/VAsin (vasin [x] (vmap-md p/vasin x))
  p/VAtan (vatan [x] (vmap-md p/vatan x))
  p/VCosh (vcosh [x] (vmap-md p/vcosh x))
  p/VSinh (vsinh [x] (vmap-md p/vsinh x))
  p/VTanh (vtanh [x] (vmap-md p/vtanh x))
  p/VTestNeg (vtest-neg? [x] (vtest-md p/vtest-neg? x))
  p/VTestEQ (vtest-eq? [x1 x2] (vtest-md p/vtest-eq? x1 x2))
  p/VTestNE (vtest-ne? [x1 x2] (vtest-md p/vtest-ne? x1 x2))
  p/VTestLT (vtest-lt? [x1 x2] (vtest-md p/vtest-lt? x1 x2))
  p/VTestLE (vtest-le? [x1 x2] (vtest-md p/vtest-le? x1 x2))
  p/VTestGT (vtest-gt? [x1 x2] (vtest-md p/vtest-gt? x1 x2))
  p/VTestGE (vtest-ge? [x1 x2] (vtest-md p/vtest-ge? x1 x2))
  p/VBitAnd (vbit-and [x1 x2] (vmap-md p/vbit-and x1 x2))
  p/VBitOr (vbit-or [x1 x2] (vmap-md p/vbit-or x1 x2))
  p/VBitXor (vbit-xor [x1 x2] (vmap-md p/vbit-xor x1 x2))
  p/VBitNot (vbit-not [x] (vmap-md p/vbit-not x))
  p/VBitAndNot (vbit-and-not [x1 x2] (vmap-md p/vbit-and-not x1 x2)))

