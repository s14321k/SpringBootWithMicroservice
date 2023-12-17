CREATE DATABASE IF NOT EXISTS PROJECTDB;

USE PROJECTDB;

create table if not exists PROJECTDB.location
(
    id   int          not null
    primary key,
    code varchar(255) null,
    name varchar(255) null,
    type varchar(255) null
    );

create table if not exists PROJECTDB.studenttab
(
    id      bigint       not null
    primary key,
    scourse varchar(255) null,
    sfee    double       null,
    sname   varchar(255) null
    );

create table if not exists PROJECTDB.studenttab_seq
(
    next_val bigint null
);

