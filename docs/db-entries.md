# Quick SQL DB with content
Create this. \
Adding the data takes about 10 minutes, it creates about 3GB of data.

'''sql
DROP DATABASE pgjdbc;
CREATE DATABASE pgjdbc;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS ticketing_system (
    id BIGSERIAL,
    ticket_id UUID not null default uuid_generate_v4(),
    count int,
    created_at timestamptz NOT NULL
);

CREATE INDEX IF NOT EXISTS ticketing_system_created_at_index ON ticketing_system USING brin(created_at); 

INSERT INTO ticketing_system (count, created_at)
SELECT floor(random() * 10 + 1)::int, dt
FROM generate_series('2015-01-01 0:00'::timestamptz,
'2021-04-23 23:59:50'::timestamptz, '5 seconds'::interval) dt;
'''