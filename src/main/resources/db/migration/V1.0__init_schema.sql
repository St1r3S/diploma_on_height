CREATE TABLE addresses
(
    id          BIGSERIAL PRIMARY KEY,
    country     TEXT NOT NULL,
    city        TEXT NOT NULL,
    address     TEXT NOT NULL,
    zip_code    TEXT NOT NULL,
    last_update DATE NOT NULL,
    UNIQUE (country, city, address, zip_code)
);

CREATE TABLE companies
(
    id              BIGSERIAL PRIMARY KEY,
    company_name    TEXT UNIQUE NOT NULL,
    corporate_email TEXT UNIQUE NOT NULL,
    corporate_phone TEXT UNIQUE NOT NULL
);

CREATE TABLE warehouses
(
    id             BIGSERIAL PRIMARY KEY,
    classification TEXT        NOT NULL,
    description    TEXT,
    company_id     BIGINT      NOT NULL REFERENCES companies (id) ON UPDATE CASCADE ON DELETE CASCADE,
    address_id     BIGINT      NOT NULL REFERENCES addresses (id) ON UPDATE CASCADE ON DELETE CASCADE,
    phone_number   TEXT UNIQUE NOT NULL
);

CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,
    user_role     TEXT        NOT NULL,
    first_name    TEXT        NOT NULL,
    last_name     TEXT        NOT NULL,
    email         TEXT UNIQUE NOT NULL,
    phone_number  TEXT UNIQUE NOT NULL,
    username      TEXT UNIQUE NOT NULL,
    password_hash TEXT        NOT NULL,
    birthday      DATE        NOT NULL,
    gender        TEXT        NOT NULL,
    department    TEXT        NOT NULL,
    post          TEXT        NOT NULL,
    company_id    BIGINT      NOT NULL REFERENCES companies (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE products
(
    id             BIGSERIAL PRIMARY KEY,
    product_type   TEXT   NOT NULL,
    product_name   TEXT   NOT NULL,
    product_qty    INT    NOT NULL,
    unit_price     INT    NOT NULL,
    price_currency TEXT   NOT NULL,
    warehouse_id   BIGINT NOT NULL REFERENCES warehouses (id) ON UPDATE CASCADE ON DELETE CASCADE,
    company_id     BIGINT NOT NULL REFERENCES companies (id) ON UPDATE CASCADE ON DELETE CASCADE,
    UNIQUE (product_type, product_name, warehouse_id, company_id)
);

CREATE TABLE requests
(
    id               BIGSERIAL PRIMARY KEY,
    first_name       TEXT        NOT NULL,
    last_name        TEXT        NOT NULL,
    email            TEXT UNIQUE NOT NULL,
    phone_number     TEXT UNIQUE NOT NULL,
    company_id       BIGINT      NOT NULL REFERENCES companies (id) ON UPDATE CASCADE ON DELETE CASCADE,
    delivery_method  TEXT        NOT NULL,
    delivery_address TEXT        NOT NULL,
    payment_method   TEXT        NOT NULL,
    comment          TEXT
);

CREATE TABLE completion_stage
(
    id                  BIGSERIAL PRIMARY KEY,
    request_status      TEXT      NOT NULL,
    completion_datetime TIMESTAMP NOT NULL,
    request_id          BIGINT    NOT NULL REFERENCES requests (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE products_requests
(
    product_id  BIGINT NOT NULL REFERENCES products (id) ON UPDATE CASCADE ON DELETE CASCADE,
    request_id  BIGINT NOT NULL REFERENCES requests (id) ON UPDATE CASCADE ON DELETE CASCADE,
    product_qty INT    NOT NULL,
    PRIMARY KEY (product_id, request_id)
);

CREATE TABLE contacts
(
    id                  BIGSERIAL PRIMARY KEY,
    first_name          TEXT        NOT NULL,
    last_name           TEXT        NOT NULL,
    email               TEXT UNIQUE NOT NULL,
    phone_number        TEXT UNIQUE NOT NULL,
    contact_description TEXT,
    company_id          BIGINT      NOT NULL REFERENCES companies (id) ON UPDATE CASCADE ON DELETE CASCADE
);