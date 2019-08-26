(ns clojure-ans.ans21-30)

;;21  nth
(= (#(first(drop %2 %1)) '(4 5 6 7) 2) 6)
(= (#(first(drop %2 %1)) [:a :b :c] 0) :a)
(= (#(first(drop %2 %1)) [1 2 3 4] 1) 2)
(= (#(first(drop %2 %1)) '([1 2] [3 4] [5 6]) 2) [5 6])

(nth '([1 2] [3 4] [5 6]) 2)

;;22 count
(= (count '(1 2 3 3 1)) 5)
(= (count "Hello World") 11)
(= (count [[1 2] [3 4] [5 6]]) 3)
(= (count '(13)) 1)
(= (count '(:a :b :c)) 3)
;; (fn [sequence] (reduce (fn [acc v] (inc acc)) 0 sequence))

;;; 23 reverse
(= (reverse [1 2 3 4 5]) [5 4 3 2 1])
(= (reverse (sorted-set 5 7 2 7)) '(7 5 2))
(= (reverse [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]])
;;;  apply conj ()

;;; 24 apply
(= (apply + [1 2 3]) 6)
(= (apply + (list 0 -2 5 5)) 8)
(= (apply + #{4 2 1}) 7)
(= (apply + '(0 0 -1)) -1)
(= (apply + '(1 10 3)) 14)

;; 25
(= (filter odd? #{1 2 3 4 5}) '(1 3 5))
(= (filter odd? [4 2 1 6]) '(1))
(= (filter odd? [2 2 4 6]) '())
(= (filter odd? [1 1 1 3]) '(1 1 1 3))


;;; 26
(= (__ 3) '(1 1 2))
(= (__ 6) '(1 1 2 3 5 8))
(= (__ 8) '(1 1 2 3 5 8 13 21))

;;; 27 回文判断


;;;; 28 flatten
(= (flatten '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (flatten ["a" ["b"] "c"]) '("a" "b" "c"))
(= (flatten '((((:a))))) '(:a))

;;; 29
(defn f [x]
  (apply str (filter #(Character/isUpperCase %) (seq x))))
(= (f "HeLlO, WoRlD!") "HLOWRD")
(empty? (f "nothing"))
(= (f "$#A(*&987Zf") "AZ")


;;;;;  partition-by
(= (apply str (#(map first (partition-by identity %)) "Leeeeeerrroyyy")) "Leroy")
(= (#(map first (partition-by identity %)) [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(= (#(map first (partition-by identity %)) [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))

