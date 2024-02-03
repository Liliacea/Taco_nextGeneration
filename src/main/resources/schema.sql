CREATE TABLE IF NOT EXISTS users
(
    id        INT         NOT NULL GENERATED ALWAYS AS IDENTITY,
    primary key (id),
    username  VARCHAR(45) NULL,
    password  VARCHAR(100) NOT NULL,
    fullname VARCHAR(45) NOT NULL,
    street VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    state VARCHAR(45) NOT NULL,
    zip VARCHAR(45) NOT NULL,
    phone VARCHAR(45) NOT NULL

);
CREATE TABLE IF NOT EXISTS authorities
(
    id   INT         NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(45) NULL,
    users INT NOT NULL ,

    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS product
(
    id       INT         NOT NULL GENERATED ALWAYS AS IDENTITY,
    name     VARCHAR(45) NULL,
    price    VARCHAR(45) NULL,
    currency VARCHAR(45) NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS ingredient
(
    id        VARCHAR(45)         NOT NULL,

    name  VARCHAR(45) NULL,

    type VARCHAR(45) NOT NULL
);
CREATE TABLE IF NOT EXISTS taco
(
    id       int         NOT NULL GENERATED ALWAYS AS IDENTITY,
    primary key (id),
    name     VARCHAR(45) NULL,
    date    date not NULL,
    currency VARCHAR(45) NULL,
    ingredients varchar(45)

);
CREATE TABLE IF NOT EXISTS taco_order
(
    id       int         NOT NULL GENERATED ALWAYS AS IDENTITY,
    primary key (id),
    placed_At     date not NULL,
    delivery_Name    VARCHAR(45) NULL,
    delivery_Street VARCHAR(45) NULL,
    delivery_City    VARCHAR(45) NULL,
    delivery_State VARCHAR(45) NULL,
    delivery_Zip    VARCHAR(45) NULL,
    cc_Number VARCHAR(45) NULL,
    cc_Expiration    VARCHAR(45) NULL,
    ccCVV VARCHAR(45) NULL,
    user_id int null,

    tacos varchar(45)

);


