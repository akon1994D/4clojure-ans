(ns clojure-ans.ans61-70)


; 61
(= (#(apply hash-map (interleave % %2)) [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})
(= (#(apply hash-map (interleave % %2)) [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})
(= (#(apply hash-map (interleave % %2)) [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"})

;#(-> (fn [ass x] (assoc ass (first x) (second x)))
;    (reduce {}  (map list %1 %2)))

;;;; 62  (fn it [f x] (cons x (lazy-seq (it f (f x)))))  TODO
(= (take 5 ((fn it [f x] (cons x (lazy-seq (it f (f x))))) #(* 2 %) 1)) [1 2 4 8 16])
(= (take 100 ((fn it [f x] (cons x (lazy-seq (it f (f x))))) inc 0)) (take 100 (range)))
(= (take 9 ((fn it [f x] (cons x (lazy-seq (it f (f x))))) #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3])))

;;;; 63  merge-with
(= (#(apply merge-with concat (map (fn [e] {(%1 e) [e]}) %2))
     #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})

(= (#(apply merge-with concat (map (fn [e] {(%1 e) [e]}) %2))
     #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})

(= (#(apply merge-with concat (map (fn [e] {(%1 e) [e]}) %2))
     count [[1] [1 2] [3] [1 2 3] [2 3]])
   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})

;#(reduce (fn [acc e]
;           (if (acc (first e))
;             (assoc acc (first e) (conj (acc (first e)) (second e)))
;             (assoc acc (first e) [(second e)]))) {} (map list %1 %2))
;; (#(apply merge-with concat (map (fn [e] {(%1 e) [e]}) %2)) #(> % 5) [1 3 6 8])
;;

;;; 64
(= 15 (reduce + [1 2 3 4 5]))
(=  0 (reduce + []))
(=  6 (reduce + 1 [2 3]))

;;;; 65
(= :map ((fn [coll]
           (cond
             (map? coll) :map
             (list? coll) :list
             (vector? coll) :vector
             (set? coll) :set
             )) {:a 1, :b 2}))
(= :list (__ (range (rand-int 20))))
(= :vector (__ [1 2 3 4 5 6]))
(= :set (__ #{10 (rand-int 5)}))
(= [:map :set :vector :list] (map __ [{} #{} [] ()]))


;;;; 66
(= ((fn gcd [x y]
      (if (< x y) (gcd y x)
                  (let [d (- x y)]
                    (if (= d 0)
                      y
                      (gcd y d))))) 2 4) 2)
(= ((fn gcd [x y]
      (if (< x y) (gcd y x)
                  (let [d (- x y)]
                    (if (= d 0)
                      y
                      (gcd y d))))) 10 5) 5)
(= ((fn gcd [x y]
      (if (< x y) (gcd y x)
                  (let [d (- x y)]
                    (if (= d 0)
                      y
                      (gcd y d))))) 5 7) 1)
(= ((fn gcd [x y]
      (if (< x y) (gcd y x)
                  (let [d (- x y)]
                    (if (= d 0)
                      y
                      (gcd y d))))) 1023 858) 33)

;;;; 67
;(= (__ 2) [2 3])
;(= (__ 5) [2 3 5 7 11])
;(= (last (__ 100)) 541)

;;; 68
;;; recur
; Clojure only has one non-stack-consuming looping construct: recur. Either a function or a loop can be used as the recursion point. Either way, recur rebinds the bindings of the recursion point to the values it is passed. Recur must be called from the tail-position, and calling it elsewhere will result in an error.
(= [7 6 5 4 3]
   (loop [x 5
          result []]
     (if (> x 0)
       (recur (dec x) (conj result (+ 2 x)))
       result)))

;;;; 69 merge-with
(= (merge-with * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
   {:a 4, :b 6, :c 20})
(= (merge-with - {1 10, 2 20} {1 3, 2 10, 3 15})
   {1 7, 2 10, 3 15})
(= (merge-with concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
   {:a [3 4 5], :b [6 7], :c [8 9]})


;;;; 70
(= ((fn [s] (sort-by #(.toLowerCase %) (re-seq #"\w+" s))) "Have a nice day.")
   ["a" "day" "Have" "nice"])
(= ((fn [s] (sort-by #(.toLowerCase %) (re-seq #"\w+" s)))  "Clojure is a fun language!")
   ["a" "Clojure" "fun" "is" "language"])
(= ((fn [s] (sort-by #(.toLowerCase %) (re-seq #"\w+" s)))  "Fools fall for foolish follies.")
   ["fall" "follies" "foolish" "Fools" "for"])

