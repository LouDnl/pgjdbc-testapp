-- :name search-and-count
-- :doc Search tickets
-- :command :query
-- :result :many
-- Accepts:
--      :id                 Filter on id
--      :ticket_id          Filter on content of ticket_id
--      :start-date-from    Filter tickets starting from this date
--      :start-date-to      Filter tickets starting to this date
SELECT ticketing_system.id, ticketing_system.ticket_id AS ticketid, ticketing_system.count, ticketing_system.created_at, count(ticketing_system.id) OVER() as totaltickets
FROM ticketing_system
/*~ (when (or (:id params) (:ticket_id params) (:start-date-from params) (:start-date-to params))
  (str "WHERE "
       (clojure.string/join " AND "
                            (remove nil?
                                    [(when (:ticket_id params)
                                       (str
                                        "ticketing_system.ticket_id ILIKE :text"))
                                     (when (:start-date-from params) "ticketing_system.created_at >= TO_DATE(:start-date-from, 'DD-MM-YYYY')")
                                     (when (:start-date-to params) "ticketing_system.created_at <= TO_DATE(:start-date-to, 'DD-MM-YYYY') + interval '1 day'")
                                     ])))) ~*/    
ORDER BY ticketing_system.created_at DESC
LIMIT :psize
    OFFSET :offset;
