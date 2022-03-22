CREATE TABLE public.customer
(
    id         uuid PRIMARY KEY,
    email      text unique           not null,
    first_name text,
    last_name  text,
    gender     text,
    birthday   date,
    premium    boolean default false not null,
    created    timestamp without time zone,
    modified   timestamp without time zone
);
