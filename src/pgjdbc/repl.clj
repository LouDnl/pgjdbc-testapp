(ns pgjdbc.repl
   (:require
    [nrepl.server :refer [start-server stop-server]]
    [environ.core :refer (env)]
    [cider.nrepl :refer (cider-nrepl-handler)]
    [refactor-nrepl.middleware :refer (wrap-refactor)]
    [mount.lite :refer (defstate)]))

  (defstate repl
    :start (if-let [port (some-> env :repl-port Integer/parseInt)]
             (do (println "Starting nREPL server at port:" port)
                 (start-server :port port
                               :bind "127.0.0.1"
                               :handler (wrap-refactor cider-nrepl-handler)))
             (do (println "Not starting nREPL server: no REPL_PORT configured.")
                 nil))
    :stop (when repl
            (println "Stopping nREPL server...")
            (stop-server repl)))