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
(= (__ [:a :b] 4) '(:a :a :a :a :b :b :b :b))
(= (__ [4 5 6] 1) '(4 5 6))
(= (__ [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
(= (__ [44 33] 2) [44 44 33 33])

;;;;; 34
(= (range 1 4) '(1 2 3))
(= (range -2 2) '(-2 -1 0 1))
(= (range 5 8) '(5 6 7))
;;;; #(take (- %2 %1) (iterate inc %1))