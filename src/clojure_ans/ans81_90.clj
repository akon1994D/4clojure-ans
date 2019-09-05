(ns clojure-ans.ans81-90)



;;; 81
(= (clojure.set/intersection #{0 1 2 3} #{2 3 4 5}) #{2 3})
(= (clojure.set/intersection #{0 1 2} #{3 4 5}) #{})
(= (clojure.set/intersection #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})
