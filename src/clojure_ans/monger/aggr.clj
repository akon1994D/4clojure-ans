(ns clojure-ans.monger.aggr
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [clojure.test :refer :all]
            [monger.operators :refer :all]))

(let [conn (mg/connect {:host "47.107.128.245" :port 8087})
      db   (mg/get-db conn "test2")
      coll "docs"]
  (defn purge-collections
    [f]
    (mc/purge-many db [coll])
    (f)
    (mc/purge-many db [coll]))

  (use-fixtures :each purge-collections)

  (deftest test-basic-single-stage-$project-aggregation-no-keywordize
    (let [batch      [{"state" "CA" "quantity" 1 "price" 199.00}
                      {"state" "NY" "quantity" 2 "price" 199.00}
                      {"state" "NY" "quantity" 1 "price" 299.00}
                      {"state" "IL" "quantity" 2 "price" 11.50 }
                      {"state" "CA" "quantity" 2 "price" 2.95  }
                      {"state" "IL" "quantity" 3 "price" 5.50  }]
          expected    #{{"quantity" 1 "state" "CA"}
                        {"quantity" 2 "state" "NY"}
                        {"quantity" 1 "state" "NY"}
                        {"quantity" 2 "state" "IL"}
                        {"quantity" 2 "state" "CA"}
                        {"quantity" 3 "state" "IL"}}]
      (mc/insert-batch db coll batch)
      (is (= 6 (mc/count db coll)))
      (let [result (->>
                     (mc/aggregate db coll [{$project {"state" 1 "quantity" 1}}] :keywordize false)
                     (map #(select-keys % ["state" "quantity"]))
                     (set))]
        (is (= expected result)))))

  (deftest test-basic-single-stage-$project-aggregation
    (let [batch      [{:state "CA" :quantity 1 :price 199.00}
                      {:state "NY" :quantity 2 :price 199.00}
                      {:state "NY" :quantity 1 :price 299.00}
                      {:state "IL" :quantity 2 :price 11.50 }
                      {:state "CA" :quantity 2 :price 2.95  }
                      {:state "IL" :quantity 3 :price 5.50  }]
          expected    #{{:quantity 1 :state "CA"}
                        {:quantity 2 :state "NY"}
                        {:quantity 1 :state "NY"}
                        {:quantity 2 :state "IL"}
                        {:quantity 2 :state "CA"}
                        {:quantity 3 :state "IL"}}]
      (mc/insert-batch db coll batch)
      (is (= 6 (mc/count db coll)))
      (let [result (set (map #(select-keys % [:state :quantity])
                             (mc/aggregate db coll [{$project {:state 1 :quantity 1}}])))]
        (is (= expected result)))))


  (deftest test-basic-projection-with-multiplication
    (let [batch      [{:state "CA" :quantity 1 :price 199.00}
                      {:state "NY" :quantity 2 :price 199.00}
                      {:state "NY" :quantity 1 :price 299.00}
                      {:state "IL" :quantity 2 :price 11.50 }
                      {:state "CA" :quantity 2 :price 2.95  }
                      {:state "IL" :quantity 3 :price 5.50  }]
          expected    #{{:_id "NY" :subtotal 398.0}
                        {:_id "NY" :subtotal 299.0}
                        {:_id "IL" :subtotal 23.0}
                        {:_id "CA" :subtotal 5.9}
                        {:_id "IL" :subtotal 16.5}
                        {:_id "CA" :subtotal 199.0}}]
      (mc/insert-batch db coll batch)
      (let [result (set (mc/aggregate db coll [{$project {:subtotal {$multiply ["$quantity", "$price"]}
                                                          :_id     "$state"}}]))]
        (is (= expected result)))))


  (deftest test-basic-total-aggregation
    (let [batch      [{:state "CA" :quantity 1 :price 199.00}
                      {:state "NY" :quantity 2 :price 199.00}
                      {:state "NY" :quantity 1 :price 299.00}
                      {:state "IL" :quantity 2 :price 11.50 }
                      {:state "CA" :quantity 2 :price 2.95  }
                      {:state "IL" :quantity 3 :price 5.50  }]
          expected    #{{:_id "CA" :total 204.9} {:_id "IL" :total 39.5} {:_id "NY" :total 697.0}}]
      (mc/insert-batch db coll batch)
      (let [result (set (mc/aggregate db coll [{$project {:subtotal {$multiply ["$quantity", "$price"]}
                                                          :_id      1
                                                          :state   1}}
                                               {$group   {:_id   "$state"
                                                          :total {$sum "$subtotal"}}}]))]
        (is (= expected result)))))


  (deftest test-$first-aggregation-operator
    (let [batch      [{:state "CA"}
                      {:state "IL"}]
          expected   "CA"]
      (mc/insert-batch db coll batch)
      (let [result (:state (first (mc/aggregate db coll [{$group {:_id 1 :state {$first "$state"}}}])))]
        (is (= expected result)))))

  (deftest test-$last-aggregation-operator
    (let [batch      [{:state "CA"}
                      {:state "IL"}]
          expected   "IL"]
      (mc/insert-batch db coll batch)
      (let [result (:state (first (mc/aggregate db coll [{$group {:_id 1 :state {$last "$state"}}}])))]
        (is (= expected result)))))

  (deftest test-cursor-aggregation
    (let [batch      [{:state "CA" :quantity 1 :price 199.00}
                      {:state "NY" :quantity 2 :price 199.00}
                      {:state "NY" :quantity 1 :price 299.00}
                      {:state "IL" :quantity 2 :price 11.50 }
                      {:state "CA" :quantity 2 :price 2.95  }
                      {:state "IL" :quantity 3 :price 5.50  }]
          expected    #{{:quantity 1 :state "CA"}
                        {:quantity 2 :state "NY"}
                        {:quantity 1 :state "NY"}
                        {:quantity 2 :state "IL"}
                        {:quantity 2 :state "CA"}
                        {:quantity 3 :state "IL"}}]
      (mc/insert-batch db coll batch)
      (is (= 6 (mc/count db coll)))
      (let [result (set (map #(select-keys % [:state :quantity])
                             (mc/aggregate db coll [{$project {:state 1 :quantity 1}}] :cursor {:batch-size 10})))]
        (is (= expected result)))))

  (deftest test-explain-aggregate
    (let [batch [{:state "CA" :price 100}
                 {:state "CA" :price 10}
                 {:state "IL" :price 50}]
          expected-keys #{:ok :stages}]
      (mc/insert-batch db coll batch)
      (let [result (mc/explain-aggregate db coll [{$match {:state "CA"}}])
            key-in-result? (partial contains? result)]
        (is (every? key-in-result? expected-keys))))))


;;; (mc/aggregate db coll [{$group {:_id ,:state {$first "$state"},:count {$sum 1}}}] :cursor true)
;;;;
;;;;;
