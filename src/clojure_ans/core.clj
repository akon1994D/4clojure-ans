(ns clojure-ans.core
  (:import [java_ans Person])
  (:gen-class))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn foo2 []
  (Person. "tom" 12))

(defn -main
  []
  (println (.getName (foo2))))