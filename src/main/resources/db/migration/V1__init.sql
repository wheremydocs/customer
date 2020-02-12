CREATE TABLE public.customer (
  id uuid PRIMARY KEY,
  username text,
  email text,
  gender Gender,
  blocked boolean,
  admin boolean,
  premium boolean,
  created timestamp without timezone,
  modified timestamp without timezone
);
