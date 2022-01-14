# How to
* Create the DB including entries with the SQL commands from [db-entries](db-entries.md)
* install leinigen
* after installation run command from main project folder `lein run`
* to switch `org.postgresql/postgresql` version uncomment one and comment the other in `project.clj`

# Repl
If you want to use the repl, comment the 4 lines as stated in selector.clj. \
Then start the project with `lein repl`. \
Now start the project with `mount/start`. \
From within the repl switch to the selector namespace using `(ns pgjdbc.selector)`. \
Now you can do db searches from the repl. \
I only implemented 4 variables for the search in `selector.clj`. The `search.sql` file has more variables but they are not implemented. \
Examples: \
`(do-search pagesize offset start-date end-date)` \
`(do-search 20 0 "01-01-2015" "01-01-2015")`

