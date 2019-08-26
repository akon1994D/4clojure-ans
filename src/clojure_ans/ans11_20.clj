(ns clojure-ans.ans11-20)


;;; 11
(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))
(= {:a 1, :b 2, :c 3} (conj {:a 1} [:b 2] [:c 3]))

;;; 12
(= 3 (first '(3 2 1)))
(= 3 (second [2 3 4]))
(= 3 (last (list 1 2 3)))

;;; 13
(= '(20 30 40) (rest [10 20 30 40]))


;;; 14
(= 8 ((fn add-five [x] (+ x 5)) 3))
(= 8 ((fn [x] (+ x 5)) 3))
(= 8 (#(+ % 5) 3))
(= 8 ((partial + 5) 3))

;;;;15
(= (#(* % 2) 2) 4)
(= (#(* % 2) 3) 6)

;;;; 16
(= (#(str "Hello, " % "!") "Dave") "Hello, Dave!")

;;; 17
(= '(6 7 8) (map #(+ % 5) '(1 2 3)))
;;; 18
(= '(6 7) (filter #(> % 5) '(3 4 5 6 7)))

;; 19
(= (last [1 2 3 4 5]) 5)
(= (last '(5 4 3)) 3)
(= (last ["b" "c" "d"]) "d")
; (comp first reverse)

;; 20
(= ((comp second reverse) (list 1 2 3 4 5)) 4)
(= ((comp second reverse) ["a" "b" "c"]) "b")
(= ((comp second reverse) [[1 2] [3 4]]) [1 2])








