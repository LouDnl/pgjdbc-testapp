(ns pgjdbc.db
  (:require [environ.core :refer (env)]
            [hikari-cp.core :as hikari]
            [mount.lite :as mount :refer (defstate)])
  (:gen-class))

(defn hikari-config
  "Create a Hikari configuration map, based on the environment."
  []
  {:adapter       "postgresql"
   :username      (env :db-username)
   :password      (env :db-password)
   :database-name (env :db-name)
   :server-name   (env :db-host)
   :port-number   (env :db-port)})

(defstate datasource
  "The connection pool datasource to the database."
  :start (let [config (hikari-config)]
           (println "Starting database connection pool...")
           {:datasource (hikari/make-datasource config)})
  :stop (do (println "Closing database connection pool...")
            (.close (:datasource datasource))))
