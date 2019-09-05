(ns clojure-ans.ans81-90)



;;; 81
(= (clojure.set/intersection #{0 1 2 3} #{2 3 4 5}) #{2 3})
(= (clojure.set/intersection #{0 1 2} #{3 4 5}) #{})
(= (clojure.set/intersection #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})
;(#(-> (fn [acc x]
;        (if (contains? % x)
;          (conj acc x)
;          acc))
;      (reduce #{} %2)) #{0 1 2 3} #{2 3 4 5})
; (comp set filter)


;;;; 82
(= true (__ #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))
(= false (__ #{"cot" "hot" "bat" "fat"}))
(= false (__ #{"to" "top" "stop" "tops" "toss"}))
(= true (__ #{"spout" "do" "pot" "pout" "spot" "dot"}))
(= true (__ #{"share" "hares" "shares" "hare" "are"}))
(= false (#(chain (list %) (list %)) #{"share" "hares" "hare" "are"}))

;;;
(defn suitable? [s1 s2]
  (let [set1 (set s1)
        set2 (set s2)
        dif1-count (count (clojure.set/difference set1 set2))
        dif2-count (count (clojure.set/difference set2 set1))]
    (and (= dif1-count 1)
         (= dif2-count 1))))
(defn chain [xs coll]
  (if (seq coll)
    (if (seq xs)
      (let [ x (first xs)
            next (filter (partial suitable? x) coll)]
        (or (chain next (remove (partial = x) coll))
            (chain (rest xs) coll)))
      false)
    true))

