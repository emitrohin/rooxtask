DROP TABLE IF EXISTS partner_mapping;
DROP TABLE IF EXISTS customers;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE GLOBAL_SEQ START WITH 100000;

CREATE TABLE customers
(
  id          INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  login       VARCHAR(255) NOT NULL,
  password    VARCHAR(255) NOT NULL,
  balance     INTEGER      NOT NULL,
  first_name  VARCHAR(255) NOT NULL,
  last_name   VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255) NOT NULL,
  enabled     BOOLEAN DEFAULT TRUE,
  CONSTRAINT ix_login UNIQUE (login)
);

CREATE TABLE partner_mapping
(
  id                  INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  customer_id         INTEGER      NOT NULL,
  partner_id          VARCHAR(255) NOT NULL,
  partner_customer_id VARCHAR(255) NOT NULL,
  last_name           VARCHAR(255),
  first_name          VARCHAR(255),
  middle_name         VARCHAR(255),
  avatar_image        VARCHAR(255),

  CONSTRAINT ix_customer_id_partner_id UNIQUE (customer_id, partner_id),
  FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);