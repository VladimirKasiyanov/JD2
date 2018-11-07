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

INSERT INTO store.person (name, surename, login, password, email, phone)
VALUES ('Ivan', 'Ivanov', 'Ivan', 'ivan', 'ivan@tut.by', '375-29-123-45-67'),
       ('Petr', 'Petrov', 'Petr', 'petr', 'petr@gmail.comy', '375-29-231-45-67'),
       ('Vasily', 'Vasechkin', 'Vasily', 'vasily', 'vasily@mail.ru', '375-29-469-45-67'),
       ('Alexandr', 'Alexandrov', 'Alexandr', 'alexandr', 'alexandr@yandex.com', '375-29-892-45-67');

CREATE TABLE store.buyer (
  id        BIGSERIAL PRIMARY KEY,
  street    CHARACTER VARYING(45) NOT NULL,
  house     INTEGER               NOT NULL,
  apartment INTEGER               NOT NULL,
  person_id BIGINT                NOT NULL REFERENCES store.person (id)
);

INSERT INTO store.buyer (street, house, apartment, person_id)
VALUES ('Независимости', '126', '12', '2'),
       ('Аэродромная', '14', '3', '3'),
       ('Лынькова', '22', '62', '4');

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

INSERT INTO store.role (name)
VALUES ('administrator'),
       ('manager');

CREATE TABLE store.employee (
  id        BIGSERIAL PRIMARY KEY,
  salary    DOUBLE PRECISION NOT NULL,
  person_id BIGINT REFERENCES store.person (id),
  role_id   BIGINT REFERENCES store.role (id)
);

INSERT INTO store.employee (salary, person_id, role_id)
VALUES ('1200', '1', '1');

CREATE TABLE store.good_category (
  id   BIGSERIAL PRIMARY KEY,
  name CHARACTER VARYING(45) UNIQUE NOT NULL
);

INSERT INTO store.good_category (name)
VALUES ('Ноутбуки'),
       ('Холодильники'),
       ('Пылесосы');

CREATE TABLE store.good (
  id          BIGSERIAL PRIMARY KEY,
  name        CHARACTER VARYING(45) UNIQUE NOT NULL,
  price       DOUBLE PRECISION             NOT NULL,
  discount    DOUBLE PRECISION,
  category_id BIGINT REFERENCES store.good_category (id)
);

INSERT INTO store.good (name, price, category_id)
VALUES ('Apple MacBook Air 13" (MQD32RU/A)', '2390.00', '1'),
       ('Xiaomi Mi Notebook Pro 15.6 JYU4034CN', '2094.94', '1'),
       ('ASUS ROG Strix SCAR II GL504GM-ES026T', '1945.50', '1'),
       ('Samsung RB33J3420BC/WT', '1099.88', '2'),
       ('Indesit DF 5200 S', '799.95', '2'),
       ('LG GA-B429SBQZ', '1438.00', '2'),
       ('Вертикальный портативный Kitfort KT-510', '79.12', '3'),
       ('Samsung SC18M21A0SB', '168.00', '3'),
       ('Робот-пылесос iRobot Roomba 980', '1700.00', '3');

CREATE TABLE store.good_description (
  id                BIGSERIAL PRIMARY KEY,
  short_description CHARACTER VARYING(256),
  full_description  TEXT,
  good_id           BIGINT UNIQUE NOT NULL REFERENCES store.good (id)
);

INSERT INTO store.good_description (short_description, full_description, good_id)
VALUES ('Краткое описание 1 ', 'Полное описание 1', '1'),
       ('Краткое описание 2 ', 'Полное описание 2', '2'),
       ('Краткое описание 3 ', 'Полное описание 3', '3'),
       ('Краткое описание 4 ', 'Полное описание 4', '4'),
       ('Краткое описание 5 ', 'Полное описание 5', '5'),
       ('Краткое описание 6 ', 'Полное описание 6', '6'),
       ('Краткое описание 7 ', 'Полное описание 7', '7'),
       ('Краткое описание 8 ', 'Полное описание 8', '8'),
       ('Краткое описание 9 ', 'Полное описание 9', '9');

CREATE TABLE store.anorder (
  id         BIGSERIAL PRIMARY KEY,
  number     INTEGER,
  order_date DATE,
  price      DOUBLE PRECISION,
  buyer_id   BIGINT REFERENCES store.buyer (id)
);

INSERT INTO store.anorder (number, order_date, price, buyer_id)
VALUES ('1', '2018-09-12', (SELECT price FROM store.good WHERE id = '1'), '3'),
       ('2', '2018-09-15', (SELECT price FROM store.good WHERE id = '5'), '2'),
       ('2', '2018-09-15', (SELECT price FROM store.good WHERE id = '8'), '2'),
       ('3', '2018-10-03', (SELECT price FROM store.good WHERE id = '9'), '1'),
       ('3', '2018-10-03', (SELECT price FROM store.good WHERE id = '8'), '1'),
       ('3', '2018-10-03', (SELECT price FROM store.good WHERE id = '2'), '1');

CREATE TABLE store.good_order (
  good_id  BIGINT NOT NULL,
  order_id BIGINT NOT NULL,
  PRIMARY KEY (good_id, order_id)
);

INSERT INTO store.good_order (good_id, order_id)
VALUES ('1', '1'),
       ('5', '2'),
       ('8', '3'),
       ('9', '4'),
       ('8', '5'),
       ('2', '6');

DROP TABLE store.buyer;

DROP TABLE store.person;

DROP TABLE store.card;

DROP TABLE store.employee;

DROP TABLE store.good;

DROP TABLE store.good_category;

DROP TABLE store.good_description;

DROP TABLE store.anorder;

DROP TABLE store.role;

DROP TABLE store.good_order;
