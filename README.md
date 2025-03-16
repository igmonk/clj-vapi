# clj-vapi

Vectorised array operations leveraging Java Vector API for SIMD parallelism.

## Prerequisites

- Clojure >= 1.12.0
- [jdk.incubator.vector](https://download.java.net/java/early_access/jdk24/docs/api/jdk.incubator.vector/module-summary.html)

Add the following module to the list of JVM options inside your `deps.edn`:

```edn
:jvm-opts ["--add-modules" "jdk.incubator.vector"]
```

## Installation

#### Clojure CLI/deps.edn

```edn
com.github.igmonk/clj-vapi {:mvn/version "0.1.0-SNAPSHOT"}
```

#### Leiningen/Boot

```edn
[com.github.igmonk/clj-vapi "0.1.0-SNAPSHOT"]
```

## Usage

The top level interface is in `clj-vapi.core`.

```clj
(use 'clj-vapi.core)
```

Use `vadd` to add two arrays element-wise:

```clj
(def sum
  (vadd (int-array (range 1000))
        (int-array (range 1000))))
```

## Benchmarks

Execution time estimation using [criterium](https://github.com/hugoduncan/criterium):

```clj
(use 'criterium.core)

(def a (int-array (range 1000)))
(def b (int-array (range 1000)))

(quick-bench (vadd a b))
```

Expected output for a CPU with SIMD registers limited to 128 bit-size:
```
Evaluation count : 1632240 in 6 samples of 272040 calls.
             Execution time mean : 397.445113 ns
    Execution time std-deviation : 48.570087 ns
   Execution time lower quantile : 355.713814 ns ( 2.5%)
   Execution time upper quantile : 460.866929 ns (97.5%)
                   Overhead used : 7.036000 ns
```

Refer to [bench/clj_vapi](bench/clj_vapi) for more benches.

## License

[MIT](LICENSE)
