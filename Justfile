deps MODE:
    clj -X:deps {{ MODE }}

fmt MODE:
    clj -M:cljfmt -m cljfmt.main {{ MODE }}

lint:
    clj -M:lint -m clj-kondo.main --lint src:test --parallel

test:
    clj -X:test

test-ns *NAMESPACES:
    clj -X:test :nses '[{{ NAMESPACES }}]'

outdated:
    clj -M:outdated

clean:
    clj -T:build clean

build:
    clj -T:build clean
    clj -T:build jar
