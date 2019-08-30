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

