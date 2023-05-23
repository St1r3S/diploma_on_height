CREATE TABLE addresses
(
    id              BIGSERIAL PRIMARY KEY,
    country         VARCHAR(50) NOT NULL,
    city            VARCHAR(50) NOT NULL,
    address         VARCHAR(50) NOT NULL,
    zip_or_postcode VARCHAR(50) NOT NULL,
    last_update     DATE        NOT NULL
);

CREATE TABLE companies
(
    id              BIGSERIAL PRIMARY KEY,
    company_name    VARCHAR(50) UNIQUE NOT NULL,
    corporate_email VARCHAR(50) UNIQUE NOT NULL,
    corporate_phone VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE warehouses
(
    id             BIGSERIAL PRIMARY KEY,
    classification VARCHAR(50)        NOT NULL,
    description    TEXT,
    company_id     BIGINT             NOT NULL REFERENCES companies (id) ON UPDATE CASCADE ON DELETE CASCADE,
    address_id     BIGINT             NOT NULL REFERENCES addresses (id) ON UPDATE CASCADE ON DELETE CASCADE,
    phone_number   VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,
    user_role     VARCHAR(20)        NOT NULL,
    first_name    VARCHAR(50)        NOT NULL,
    last_name     VARCHAR(50)        NOT NULL,
    email         VARCHAR(50) UNIQUE NOT NULL,
    phone_number  VARCHAR(50) UNIQUE NOT NULL,
    username      VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(50)        NOT NULL,
    birthday      DATE               NOT NULL,
    gender        VARCHAR(50)        NOT NULL,
    department    VARCHAR(50)        NOT NULL,
    post          VARCHAR(50)        NOT NULL,
    company_id    BIGINT             NOT NULL REFERENCES companies (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE products
(
    id             BIGSERIAL PRIMARY KEY,
    product_type   VARCHAR(50) NOT NULL,
    product_name   VARCHAR(50) NOT NULL,
    product_qty    INT         NOT NULL,
    unit_price     INT         NOT NULL,
    price_currency VARCHAR(3)  NOT NULL,
    warehouse_id   BIGINT      NOT NULL REFERENCES warehouses (id) ON UPDATE CASCADE ON DELETE CASCADE,
    company_id     BIGINT      NOT NULL REFERENCES companies (id) ON UPDATE CASCADE ON DELETE CASCADE,
    UNIQUE (product_type, product_name, warehouse_id, company_id)
);

CREATE TABLE requests
(
    id               BIGSERIAL PRIMARY KEY,
    first_name       VARCHAR(50)        NOT NULL,
    last_name        VARCHAR(50)        NOT NULL,
    email            VARCHAR(50) UNIQUE NOT NULL,
    phone_number     VARCHAR(50) UNIQUE NOT NULL,
    company_id       BIGINT             NOT NULL,
    delivery_method  VARCHAR(27)        NOT NULL,
    delivery_address VARCHAR(50)        NOT NULL,
    payment_method   VARCHAR(50)        NOT NULL,
    comment          TEXT
);

CREATE TABLE completion_stage
(
    id                  BIGSERIAL PRIMARY KEY,
    request_status      VARCHAR(15) NOT NULL,
    completion_datetime TIMESTAMP   NOT NULL,
    request_id          BIGINT      NOT NULL REFERENCES requests (id) ON UPDATE CASCADE ON DELETE CASCADE
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
    first_name          VARCHAR(50)        NOT NULL,
    last_name           VARCHAR(50)        NOT NULL,
    email               VARCHAR(50) UNIQUE NOT NULL,
    phone_number        VARCHAR(50) UNIQUE NOT NULL,
    contact_description TEXT,
    company_id          BIGINT             NOT NULL REFERENCES companies (id) ON UPDATE CASCADE ON DELETE CASCADE
);