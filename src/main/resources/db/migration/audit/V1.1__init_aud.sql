create schema if not exists audit;

CREATE TABLE audit.revinfo
(
    rev      bigint NOT NULL primary key,
    revtstmp bigint,
    username text
);

CREATE TABLE audit.customer_aud
(
    id         uuid,
    rev        bigint NOT NULL,
    revtype    smallint,
    email      text,
    first_name text,
    last_name  text,
    gender     text,
    premium    boolean,
    constraint pk_customer_aud primary key (id, rev)
);
