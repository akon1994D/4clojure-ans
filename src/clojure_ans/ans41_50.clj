(ns clojure-ans.ans41-50)

;;; 41
(= (#(apply concat (partition-all (dec %2) %2 %1)) [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
(= (#(apply concat (partition-all (dec %2) %2 %1)) [:a :b :c :d :e :f] 2) [:a :c :e])
(= (#(apply concat (partition-all (dec %2) %2 %1)) [1 2 3 4 5 6] 4) [1 2 3 5 6])
;(concat [:a :b] nil [1 [2 3] 4])
;=> (:a :b 1 [2 3] 4)
;(partition-all 2 4 [0 1 2 3 4 5 6 7 8 9])
;(#(partition-all (dec %2) %2 %1) [1 2 3 4 5 6 7 8] 3)
;=> ((1 2) (4 5) (7 8))
;

;;; 42 factorials  range 最后一个 -1 表示倒序
(= (#(reduce * (range % 0 -1)) 1) 1)
(= (#(reduce * (range % 0 -1)) 3) 6)
(= (#(reduce * (range % 0 -1)) 5) 120)
(= (#(reduce * (range % 0 -1)) 8) 40320)


;;;; 43
(= (#(apply map list (partition %2 %)) [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))
(= (#(apply map list (partition %2 %)) (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))
(= (#(apply map list (partition %2 %)) (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))
;;; #(apply map list (partition %2 %))


;;; 44
(= (#(take (count %2) (drop (mod %1 (count %2)) (concat %2 %2))) 2 [1 2 3 4 5]) '(3 4 5 1 2))
(= (#(take (count %2) (drop (mod %1 (count %2)) (concat %2 %2))) -2 [1 2 3 4 5]) '(4 5 1 2 3))
(= (#(take (count %2) (drop (mod %1 (count %2)) (concat %2 %2))) 6 [1 2 3 4 5]) '(2 3 4 5 1))
(= (#(take (count %2) (drop (mod %1 (count %2)) (concat %2 %2))) 1 '(:a :b :c)) '(:b :c :a))
(= (#(take (count %2) (drop (mod %1 (count %2)) (concat %2 %2))) -4 '(:a :b :c)) '(:c :a :b))


;;;; 45
(= '(1 4 7 10 13) (take 5 (iterate #(+ 3 %) 1)))

;;;; 46
(= 3 ((#(fn [x y] (% y x)) nth) 2 [1 2 3 4 5]))
(= true ((#(fn [x y] (% y x)) >) 7 8))
(= 4 ((#(fn [x y] (% y x)) quot) 2 8))
(= [1 2 3] ((#(fn [x y] (% y x)) take) [1 2 3 4 5] 3))

;;; 47 contains? 对于vector是有点不一样的
(contains? #{4 5 6} 4)
(contains? [1 1 1 1 1] 4)
(contains? {4 :a 2 :b} 4)
(not (contains? [1 2 4] 4))

;;;;; 48 Returns the first logical true
;;(#{2 7 5} 5)
;; => 5
;; set 可以看出一个predict
(= 6 (some #{2 7 6} [5 6 7 8]))
(= 6 (some #(when (even? %) %) [5 6 7 8]))

;;;; 49
(= (#(list (take % %2) (drop % %2)) 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
(= (#(list (take % %2) (drop % %2)) 1 [:a :b :c :d]) [[:a] [:b :c :d]])
(= (#(list (take % %2) (drop % %2)) 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])
;((juxt take drop) 3 [1 2 3 4 5 6])



;;;; 50
(= (set (#(vals (group-by type %)) [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})
(= (set (#(vals (group-by type %)) [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})
(= (set (#(vals (group-by type %)) [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})
