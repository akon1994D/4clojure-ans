(ns clojure-ans.muti.muti)

;;;;; 数据导向
;;;;; 基于数据分配的

(defmulti area :Shape)
(defn rect [wd ht] {:Shape :Rect :wd wd :ht ht})
(defn circle [radius] {:Shape :Circle :radius radius})

(defmethod area :Rect [r]
  (* (:wd r) (:ht r)))
(defmethod area :Circle [c]
  (* (. Math PI) (* (:radius c) (:radius c))))
(defmethod area :default [x] :oops)

(def r (rect 4 13))
(def c (circle 12))
(area r)
(area c)
(area {})


;;;; 基于类的disp
(defmulti foo class)
(defmethod foo java.util.Iterator [c] :a-collection)
(defmethod foo String [s] :a-string)
(foo [])
(foo "bar")