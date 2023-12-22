CREATE DATABASE IF NOT EXISTS PROJECTDB;

create table IF NOT EXISTS projectdb.location
(
    id   int          not null
        primary key,
    code varchar(255) null,
    name varchar(255) null,
    type varchar(255) null
);
