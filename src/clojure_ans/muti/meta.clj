(ns clojure-ans.muti.meta)


(meta #'+)
(meta (with-meta [1 2 3] {:my "meta"}))

;{:hi true}
(def m ^:hi [1 2 3])

;;;; {:tag java.lang.String}
(def a ^String [1 2 3] )

;;;;{:doc "aaaa"}
(def a ^{:doc "aaaa"} [1 2 3] )
(binding [*print-meta* true]
  (meta a))