(ns clojure-ans.ans51-60)



;;;  51 解构
;;;;; :as d用法
(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d]))


;;; 52
(= [2 4] (let [[a b c d e] [0 1 2 3 4]] [c e]))


;;; 53 Longest Increasing Sub-Seq  TODO
;(= (__ [1 0 1 2 3 0 4 5]) [0 1 2 3])
;(= (__ [5 6 1 3 2 7]) [5 6])
;(= (__ [2 3 3 4 5]) [3 4 5])
;(= (__ [7 6 5 4]) [])


;;; 54 split-at
(= ((fn my-partition [num s]
      (let [[x y] (split-at num s)]
        (if (<= num (count x))
          (cons x (my-partition num y))))) 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))
(= ((fn my-partition [num s]
      (let [[x y] (split-at num s)]
        (if (<= num (count x))
          (cons x (my-partition num y))))) 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
(= ((fn my-partition [num s]
      (let [[x y] (split-at num s)]
        (if (<= num (count x))
          (cons x (my-partition num y))))) 3 (range 8)) '((0 1 2) (3 4 5)))

;;;; 55  #(reduce (fn [map e] (assoc map (first e) (count (second e)))) {} (group-by identity %1))
(= (#(reduce (fn [map e] (assoc map (first e) (count (second e)))) {} (group-by identity %1)) [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})
(= (#(reduce (fn [map e] (assoc map (first e) (count (second e)))) {} (group-by identity %1)) [:b :a :b :a :b]) {:a 2, :b 3})
(= (#(reduce (fn [map e] (assoc map (first e) (count (second e)))) {} (group-by identity %1)) '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})

;;;; 56
(= ( [1 2 1 3 1 2 4]) [1 2 3 4])
(= ( [:a :a :b :b :c :c]) [:a :b :c])
(= ( '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
(= ( (range 50)) (range 50))

;#((fn duplicates  [acc s col]
;    (if (seq col)
;      (if (s (first col))
;        (duplicates acc s (rest col))
;        (duplicates (conj acc (first col)) (conj s (first col)) (rest col)))acc)) [] #{} %)
;;;;; 解构的应用
(reduce (fn [[acc s] v]
          (if (s v)
            [acc s]
            [(conj acc v) (conj s v)])) [[] #{}]  [1 2 1 3 1 2 4])
;;;;; 在用箭头函数 简化表达式
#(-> (fn [[agg s] v] (if (s v)
                       [agg s]
                       [(conj agg v) (conj s v)]))
     (reduce [[] #{}] %)
     (first))
;;(fist (reduce f [[] #{}] %))
;;; (-> f (reduce [[] #{}] %) (first))


;;; 57
(= '(5 4 3 2 1) ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))

;;; 58
(= [3 2 1] ((comp rest reverse) [1 2 3 4]))
(= 5 ((comp (partial + 3) second) [1 2 3 4]))
(= true ((comp zero? #(mod % 8) +) 3 5 7 9))
(= "HELLO" ((comp #(.toUpperCase %) #(apply str %) take) 5 "hello world"))
;;
;; (fn [& funcs]
;  (fn [& args] (first (reduce #(list (apply %2 %)) args (reverse funcs)))))

;;; 59
(= [21 6 1] ((juxt + max min) 2 3 5 1 6 4))
(= ["HELLO" 5] ((juxt #(.toUpperCase %) count) "hello"))
(= [2 6 4] ((juxt :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))


;;; 60  TODO
(= (take 5 (__ + (range))) [0 1 3 6 10])
(= (__ conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])
(= (last (__ * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120)
