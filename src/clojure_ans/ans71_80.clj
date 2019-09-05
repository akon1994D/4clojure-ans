(ns clojure-ans.ans71-80)


;;;; 71
(= (last (sort (rest (reverse [2 5 4 1 3 6]))))
   (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (last))
   5)

;;;;; 72
(= (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce + ))
   11)

;;;; 73 TODO
;(= nil (__ [[:e :e :e]
;            [:e :e :e]
;            [:e :e :e]]))
;(= :x (__ [[:x :e :o]
;           [:x :e :e]
;           [:x :e :o]]))
;(= :o (__ [[:e :x :e]
;           [:o :o :o]
;           [:x :e :x]]))
;(= nil (__ [[:x :e :o]
;            [:x :x :e]
;            [:o :x :o]]))
;(= :x (__ [[:x :e :e]
;           [:o :x :e]
;           [:o :e :x]]))
;(= :o (__ [[:x :e :o]
;           [:x :o :e]
;           [:o :e :x]]))
;(= nil (__ [[:x :o :x]
;            [:x :o :x]
;            [:o :x :o]]))

;;;;; 74
(= (__ "4,5,6,7,8,9") "4,9")
(= (__ "15,16,25,36,37") "16,25,36")



;;;; 75



;;;;;; 76 trampoline :mutual recursion without stack consumption
(= __
   (letfn
      [(foo [x y] #(bar (conj x y) y))
       (bar [x y] (if (> (last x) 10)
                     x
                     #(foo x (+ 2 y))))]
      (trampoline foo [] 1)))
;;;; foo =  bar (conj x y ) y
;;;; bar =  foo x  (+ 2 y)
;;;; 1 3 5 7 9 11


;;;;; 77 TODO
(= (__ ["meat" "mat" "team" "mate" "eat"])
   #{#{"meat" "team" "mate"}})
(= (__ ["veer" "lake" "item" "kale" "mite" "ever"])
   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})



;;;; 78
;;;;
(= (letfn [(triple [x] #(sub-two (* 3 x)))
           (sub-two [x] #(stop?(- x 2)))
           (stop? [x] (if (> x 50) x #(triple x)))]
      (trampoline triple 2))
   82)
(= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
           (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
      (map (partial trampoline my-even?) (range 6)))
   [true false true false true false])



;;;; 79
(= 7 (__ '([1]
             [2 4]
             [5 1 4]
             [2 3 4 5]))) ; 1->2->1->3
(= 20 (__ '([3]
              [2 4]
              [1 9 3]
              [9 9 2 4]
              [4 6 6 7 8]
              [5 7 3 5 1 4]))) ; 3->4->3->2->7->1

;;; 0 -> 0 1;
;;; 1 --> 1 2
;;;;