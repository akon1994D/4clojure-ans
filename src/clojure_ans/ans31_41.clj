(ns clojure-ans.ans31-41)


;;;; 31  partition-by identity
(= (#(partition-by	identity %) [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (#(partition-by	identity %) [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (#(partition-by	identity %) [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))

;;;; 32 interleave
(= (#(interleave % %) [1 2 3]) '(1 1 2 2 3 3))
(= (#(interleave % %) [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (#(interleave % %) [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
(= (#(interleave % %) [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

(mapcat #(list % %) [1 2 3])

;;;;; 33
(= (#(mapcat (fn [x] (repeat %2 x)) %) [1 2 3] 2) '(1 1 2 2 3 3))
(= (#(mapcat (fn [x] (repeat %2 x)) %) [:a :b] 4) '(:a :a :a :a :b :b :b :b))
(= (#(mapcat (fn [x] (repeat %2 x)) %) [4 5 6] 1) '(4 5 6))
(= (#(mapcat (fn [x] (repeat %2 x)) %) [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
(= (#(mapcat (fn [x] (repeat %2 x)) %) [44 33] 2) [44 44 33 33])

;;;;; 34
(= (range 1 4) '(1 2 3))
;(= (range -2 2) '(-2 -1 0 1))
;(= (range 5 8) '(5 6 7))
;;;;; #(take (- %2 %1) (iterate inc %1))


;;;;; 35
(= 7 (let [x 5] (+ 2 x)))
(= 7 (let [x 3, y 10] (- y x)))
(= 7 (let [x 21] (let [y 3] (/ x y))))


;;;;; 36
(= 10 (let [z 1 y 3 x 7] (+ x y)))
(= 4 (let [z 1 y 3 x 7] (+ y z)))
(= 1 (let [z 1 y 3 x 7] z))

;;;; 37
(= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))

;;;; 38
(= ((comp last sort list) 1 8 3 4) 8)
(= ((comp last sort list) 30 20) 30)
(= ((comp last sort list) 45 67 11) 67)

;;; 39  interleave = mapcat list
(= (interleave [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
(= (interleave [1 2] [3 4 5 6]) '(1 3 2 4))
(= (interleave [1 2 3 4] [5]) [1 5])
(= (interleave [30 20] [25 15]) [30 25 20 15])
;(mapcat list [1 2 3] [:a :b :c])
;=> (1 :a 2 :b 3 :c)
;(map list [1 2 3] [:a :b :c])
; => ((1 :a) (2 :b) (3 :c))
;(sequ  ence cat (map list [1 2 3] [:a :b :c]))
;=> (1 :a 2 :b 3 :c)


;;; 40  butlast 最后一个不区 mapcat interleave
(= (#(butlast (interleave %2 (repeat %1))) 0 [1 2 3]) [1 0 2 0 3])
(= (apply str (#(butlast  (interleave %2 (repeat %1))) ", " ["one" "two" "three"])) "one, two, three")
(= (#(butlast  (interleave %2 (repeat %1))) :z [:a :b :c :d]) [:a :z :b :z :c :z :d])















