(ns clojure-ans.ans1-10)

;1
(= true true)
;2
(= (- 10 (* 2 3)) 4)
;3  java 互操作
(= "HELLO WORLD" (.toUpperCase "hello world"))

;4 list
(= (list :a :b :c) '(:a :b :c))

;5  conj[oin]  list 是插入头部
(= '(1 2 3 4) (conj '(2 3 4) 1))
(= '(1 2 3 4) (conj '(3 4) 2 1))

;6 (= [:a :b :c] '(:a :b :c)) = 是不区分list 和 vector 但区分set
(= [:a :b :c] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))

;7  conj[oin]  vector 是尾部插入
(= '(1 2 3 4) (conj [1 2 3] 4))
(= '(1 2 3 4) (conj [1 2] 3 4))

;8 set
(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))
(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))

;9 conj[oin]  set 是无序加入
(= #{1 2 3 4} (conj #{1 4 3} 2))

;10  但键值为关键字时有2种获取value形式; 第二种只适合用在关键字为key的时候
(= 20 ((hash-map :a 10, :b 20, :c 30) :b))
(= 20 (:b {:a 10, :b 20, :c 30}))
;  (= 20 ("b" {"a" 10, "b" 20, "c" 30}))

;;;;;; JAVA的互操作
;; https://www.clojure.org/reference/java_interop
