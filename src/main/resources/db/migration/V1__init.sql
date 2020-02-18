create type public.gender_type as enum (
    'MALE',
    'FEMALE'
);

CREATE TABLE public.customer (
  id uuid PRIMARY KEY,
  username text,
  email text,
  gender public.gender_type,
  created timestamp without time zone,
  modified timestamp without time zone
);
