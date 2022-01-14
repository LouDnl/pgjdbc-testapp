(ns pgjdbc.core
  (:require [pgjdbc.repl]
            ;; [pgjdbc.db]
            [pgjdbc.selector]
            [mount.lite :as mount :refer (defstate)])
  (:gen-class))

(defn -main
  "pgjdbc main entry point."
  [& _args]
  (let [signalled (promise)
        stopped (promise)
        hook (fn []
               (println "Trigger to shutdown received...")
               (deliver signalled true)
               (when (deref stopped 45000 false)
                 (println "pgjdbc stopped gracefully.")))
        runtime (Runtime/getRuntime)]
    (println "Starting pgjdbc")
    (.addShutdownHook runtime (Thread. ^Runnable hook))
    (try
      (mount/start)
      (println "pgjdbc fully started.")
      (deref signalled)
      (finally
        (println "Stopping pgjdbc")
        (mount/stop)
        (shutdown-agents)
        (deliver stopped true)))))

