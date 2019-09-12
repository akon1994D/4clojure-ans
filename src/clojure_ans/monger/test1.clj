(ns clojure-ans.monger.test1
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.db :as md]
            [monger.operators :refer :all]))

;(def conn (mg/connect {:host "47.107.128.245" :port 8087}))
;(def db (mg/get-db conn "test"))
;;;;;
;(md/get-collection-names db)
;(md/drop-db db)
;
;;;;; gridfs
;;;;
;(mc/insert-and-return db "test1" {:name "John" :age 30})
;(mc/insert-and-return db "test1" {:name "tom" :age 20})
;;;; DBCursor 和 clojure map对象
;(mc/find db "test1" {:name "tom"})
;(mc/find-maps db "test1" {:name "tom"})

;;;;
;(mc/aggregate db "test1" [$group])

(mc/aggregate db "test1" [{$group {:name {$sum 1}}}])

(mc/aggregate db "test1" [{$project {:age "age" :_id "name"}}] :cursor true)