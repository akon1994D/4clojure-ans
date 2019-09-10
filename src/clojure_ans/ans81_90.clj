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
(= true (#(chain (seq %) (seq %)) #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"}))
(= false (#(chain (seq %) (seq %)) #{"cot" "hot" "bat" "fat"}))
(= false (#(chain (seq %) (seq %)) #{"to" "top" "stop" "tops" "toss"}))
(= true (#(chain (seq %) (seq %)) #{"spout" "do" "pot" "pout" "spot" "dot"}))
(= true (#(chain (seq %) (seq %)) #{"share" "hares" "shares" "hare" "are"}))
(= false (#(chain (seq %) (seq %)) #{"share" "hares" "hare" "are"}))
;;;
(fn [s]
  (letfn [(suitable? [x y]
            (let [diffs (fn [x y] (<= (reduce + (map #(if (= %1 %2) 0 1) x y)) 1))
                  drop-char (fn [n s] (str (subs s 0 n) (subs s (inc n) (count s))))
                  drop-variants (fn [s] (map #(drop-char % s) (range (count s))))
                  deletions (fn [l s] (some #(= s %) (drop-variants l)))
                  cx (count x)
                  cy (count y)]
              (cond
                (= cx cy) (diffs x y)
                (= cx (inc cy)) (deletions x y)
                (= (inc cx) cy) (deletions y x))))
          (chain? [xs coll]
            (if (seq coll)
              (if (seq xs)
                (let [ x (first xs)
                      next (filter (partial suitable? x) coll)]
                  (or (chain? next  (remove (partial = x) coll))
                      (chain? (rest xs) coll)))
                false)
              true))]
    (boolean
      (chain? (seq s) (seq s)))
    ))

;;;; 存在 任意
;;;; 83 #(and (not= nil (some true? %&)) (not-every? true? %&))
(= false (or false false))
(= true (or true false))
(= false (or true))
(= true (or false true false))
(= false (or true true true))
(= true (or true true true false))

;;;;; 84
(let [divides #{[8 4] [9 3] [4 2] [27 9]}]
  (= (__ divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}))
(let [more-legs
      #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
  (= (__ more-legs)
     #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
       ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}))
(let [progeny
      #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
  (= (__ progeny)
     #{["father" "son"] ["father" "grandson"]
       ["uncle" "cousin"] ["son" "grandson"]}))、


;;;; 85






