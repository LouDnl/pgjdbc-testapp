(ns pgjdbc.db.search
   (:require [hugsql.core :as hsql]))

(hsql/def-db-fns "queries/search.sql")