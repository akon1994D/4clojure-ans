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

;;;; 63
(= (__ #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})

(= (__ #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})

(= (__ count [[1] [1 2] [3] [1 2 3] [2 3]])
   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})