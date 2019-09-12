(ns clojure-ans.muti.protocol)



(defprotocol P
  (foo [this])
  (bar-me [this] [this y]))

(deftype Foo [a b c]
  P
  (foo [this] a)
  (bar-me [this] b)
  (bar-me [this y] (+ c y)))

(bar-me (Foo. 1 2 3) 42)

(foo
  (let [x 42]
    (reify P
      (foo [this] 17)
      (bar-me [this] x)
      (bar-me [this y] y))))


;;;; 把协议扩展到已有的类
(extend String
  P
  {:foo (fn [this] this)
   :bar-me (fn ([this] 11)
              ([this y] (+ this y)))})
(foo "AAA")
(bar-me "AAAA")
(bar-me "aaaa" 111)






