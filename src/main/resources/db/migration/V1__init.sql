create type public.gender_type as enum (
    'MALE',
    'FEMALE'
);

CREATE TABLE public.customer (
  id uuid PRIMARY KEY,
  username text unique,
  email text,
  gender public.gender_type,
  birthday date,
  created timestamp without time zone,
  modified timestamp without time zone
);
