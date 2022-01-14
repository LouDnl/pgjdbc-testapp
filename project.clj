(defproject pgjdbc "1.0"
  :description "pgjdbc testapp"
  :url "https://github.com/pgjdbc/pgjdbc/issues/2359"

  :dependencies [;; Our beloved Clojure
                 [org.clojure/clojure "1.10.3"]  ;; Clojure all by itself (https://clojure.org/guides/deps_and_cli)

                 ;; Database
                 [com.layerware/hugsql "0.5.1"]  ;; library for embracing SQL (https://github.com/layerware/hugsql)
                 [hikari-cp "2.13.0"]  ;; wrapper to HikariCP - "zero-overhead" production ready JDBC connection pool (https://github.com/tomekw/hikari-cp)
                 [org.clojure/java.jdbc "0.7.12"]  ;; low-level wrapper for JDBC-based access to databases  (https://github.com/clojure/java.jdbc)

                ;;  [org.postgresql/postgresql "42.3.1"]  ;; allows Java programs to connect to a PostgreSQL database (https://github.com/pgjdbc/pgjdbc)
                 [org.postgresql/postgresql "42.2.18"]  ;; allows Java programs to connect to a PostgreSQL database (https://github.com/pgjdbc/pgjdbc)

                 ;; Configuration and state management
                 [environ "1.2.0"]  ;; Manage environtment settings (https://github.com/weavejester/environ)
                 [functionalbytes/mount-lite "0.9.8"]  ;; A library resembling mount, but different on some key things (https://github.com/aroemers/mount-lite)                 

                 ;; nREPL support
                 [nrepl "0.9.0"]  ;; nREPL is a Clojure network REPL that provides a REPL server and client (https://github.com/nrepl/nrepl)
                 [cider/cider-nrepl "0.27.4"]  ;; A collection of nREPL middleware, originally designed to enhance CIDER (https://github.com/clojure-emacs/cider-nrepl)
                 [refactor-nrepl "3.1.0"]  ;; nREPL middleware to support refactorings in an editor agnostic way (https://github.com/clojure-emacs/refactor-nrepl)
                 ]
  :plugins [;; extra plugins
            [lein-ancient "0.7.0"]  ;; plugin to check your project for outdated dependencies and plugins, as well as upgrade them if desired (https://github.com/xsc/lein-ancient)
            [lein-environ "1.2.0"]  ;; plugin for the environ dependency to draw settings from the lein project map
            ]
  
  :profiles {:dev  [:project/dev :profiles/dev]
             :project/dev {:env {:environment "development"

                                 ;; development database settings
                                 :db-username "postgres"
                                 :db-password "password"
                                 :db-host     "localhost"
                                 :db-port     "5432"
                                 :db-name     "pgjdbc"

                                 ;; repl port to connect to
                                 :repl-port "1337"}}}


  :main pgjdbc.core
  :aot [pgjdbc.core])