create schema if not exists audit;

CREATE TABLE audit.revinfo
(
    rev      bigint NOT NULL primary key,
    revtstmp bigint,
    username text
);

CREATE TABLE audit.customer_aud
(
    id       uuid,
    rev      bigint NOT NULL,
    revtype  smallint,
    username text,
    email    text,
    gender   public.gender_type,
    created  timestamp without time zone,
    modified timestamp without time zone,
    constraint pk_customer_aud primary key (id, rev)
);
