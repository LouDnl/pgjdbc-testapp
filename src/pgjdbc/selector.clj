(ns pgjdbc.selector
  "This selector will auto select from the database
   and print the total count to the CLI"
  (:require [pgjdbc.db :as db]
            [pgjdbc.db.search :as search]
            [mount.lite :as mount :refer (defstate)]))



(defn do-search
  [psize offset start-date end-date]
  (let [params (cond-> {:psize psize :offset offset}
                 (seq start-date) (assoc :start-date-from start-date)
                 (seq end-date) (assoc :start-date-to end-date))
        _ (println "Starting search, hold your horses!")
        results (search/search-and-count db/datasource params)

        tcount (get (first results) :totaltickets 0)
        _ (println "Records found:" tcount)]))


;; Comment the next 4 lines if you want to be able to use the repl
(mount/start)
(do-search 20 0 "01-01-2015" "01-01-2015")
(mount/stop)
(System/exit 0)