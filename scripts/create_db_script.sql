drop database if exists patient;
create database if not exists patient default character set UTF8;
use patient;

CREATE TABLE if not exists patient
(
    id            INT AUTO_INCREMENT NOT NULL,
    first_name    VARCHAR(60)        NOT NULL,
    last_name     VARCHAR(60)        NOT NULL,
    date_of_birth VARCHAR(10)        NOT NULL,
    genre         CHAR(6)            NOT NULL,
    address       VARCHAR(100)       NOT NULL,
    phone         VARCHAR(12)        NOT NULL,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB;


