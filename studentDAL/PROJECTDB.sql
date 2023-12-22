CREATE DATABASE IF NOT EXISTS PROJECTDB;

create table IF NOT EXISTS projectdb.studenttab
(
    id      bigint       not null
        primary key,
    scourse varchar(255) null,
    sfee    double       null,
    sname   varchar(255) null
);

create table IF NOT EXISTS projectdb.studenttab_seq
(
    next_val bigint null
);