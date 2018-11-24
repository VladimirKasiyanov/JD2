CREATE SCHEMA store;

CREATE TABLE store.person (
  id       BIGSERIAL PRIMARY KEY,
  name     CHARACTER VARYING(45)        NOT NULL,
  surename CHARACTER VARYING(45)        NOT NULL,
  login    CHARACTER VARYING(45) UNIQUE NOT NULL,
  password CHARACTER VARYING(45) UNIQUE NOT NULL,
  email    CHARACTER VARYING(45) UNIQUE NOT NULL,
  phone    CHARACTER VARYING(45) UNIQUE NOT NULL
);

CREATE TABLE store.buyer (
  id        BIGSERIAL PRIMARY KEY,
  street    CHARACTER VARYING(45) NOT NULL,
  house     INTEGER               NOT NULL,
  apartment INTEGER               NOT NULL,
  person_id BIGINT                NOT NULL REFERENCES store.person (id)
);

CREATE TABLE store.card (
  id       BIGSERIAL PRIMARY KEY,
  number   CHARACTER VARYING(16) UNIQUE NOT NULL,
  name     CHARACTER VARYING(45)        NOT NULL,
  date     CHARACTER VARYING(45)        NOT NULL,
  cv       CHARACTER VARYING(45)        NOT NULL,
  buyer_id BIGINT UNIQUE                NOT NULL REFERENCES store.buyer (id)
);

CREATE TABLE store.role (
  id   BIGSERIAL PRIMARY KEY,
  name CHARACTER VARYING(45) UNIQUE NOT NULL
);

CREATE TABLE store.employee (
  id        BIGSERIAL PRIMARY KEY,
  salary    DOUBLE PRECISION NOT NULL,
  person_id BIGINT REFERENCES store.person (id),
  role_id   BIGINT REFERENCES store.role (id)
);

CREATE TABLE store.good_category (
  id   BIGSERIAL PRIMARY KEY,
  name CHARACTER VARYING(45) UNIQUE NOT NULL
);

CREATE TABLE store.good (
  id          BIGSERIAL PRIMARY KEY,
  name        CHARACTER VARYING(45) UNIQUE NOT NULL,
  price       DOUBLE PRECISION             NOT NULL,
  discount    DOUBLE PRECISION,
  category_id BIGINT REFERENCES store.good_category (id)
);

CREATE TABLE store.good_description (
  id                BIGSERIAL PRIMARY KEY,
  short_description CHARACTER VARYING(256),
  full_description  TEXT,
  good_id           BIGINT UNIQUE NOT NULL REFERENCES store.good (id)
);

CREATE TABLE store.anorder (
  id         BIGSERIAL PRIMARY KEY,
  number     INTEGER,
  order_date DATE,
  price      DOUBLE PRECISION,
  buyer_id   BIGINT REFERENCES store.buyer (id)
);

CREATE TABLE store.good_order (
  good_id  BIGINT NOT NULL REFERENCES store.good (id),
  order_id BIGINT NOT NULL REFERENCES store.anorder (id),
  PRIMARY KEY (good_id, order_id)
);