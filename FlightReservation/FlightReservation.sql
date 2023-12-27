-- https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.datatools.sqltools.doc.user%2Fdoc%2Fhtml%2Fasc1229700466482.html

-- /* -- https://www.tutorialspoint.com/In-MySQL-what-is-the-difference-between-SERIAL-and-AUTO-INCREMENT -- */

CREATE DATABASE IF NOT EXISTS RESERVATION;

create table IF NOT EXISTS reservation.user
(
    id         bigint       not null
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    password   varchar(255) null
);

select * from reservation.user;

INSERT INTO reservation.user (id, email, first_name, last_name, password)
VALUES
    (1, 'sarath@69.com', 'sarath', 'kumar', 'sarath69'),
    (2, 'user2@example.com', 'Jane', 'Smith', 'securepass456'),
    (3, 'user3@example.com', 'Bob', 'Johnson', 'letmein789');

create table IF NOT EXISTS reservation.role
(
    ID   int auto_increment
        primary key,
    NAME varchar(20) null
);

create table IF NOT EXISTS reservation.user_role
(
    role_id int    null,
    user_id bigint not null,
    constraint user_role_ibfk_1 foreign key (user_id) references reservation.user (id),
    constraint user_role_ibfk_2 foreign key (role_id) references reservation.role (ID)
);

insert into reservation.role values(1,'ADMIN');

select * from reservation.role;

insert into reservation.user_role values(1,1);

select * from reservation.user_role;

create table IF NOT EXISTS reservation.flight
(
    ID                       bigint unsigned auto_increment
        primary key,
    flight_number            varchar(255)                        null,
    operating_airlines       varchar(255)                        null,
    departure_city           varchar(255)                        null,
    arrival_city             varchar(255)                        null,
    date_of_departure        date                                null,
    ESTIMATED_DEPARTURE_TIME timestamp default CURRENT_TIMESTAMP null,
    estimate_departure_time  datetime(6)                         null,
    constraint ID
        unique (ID)
);

create table IF NOT EXISTS reservation.passenger
(
    ID          bigint unsigned auto_increment
        primary key,
    first_name  varchar(255) null,
    last_name   varchar(255) null,
    middle_name varchar(255) null,
    email       varchar(255) null,
    phone       varchar(255) null,
    constraint ID
        unique (ID)
);

create table IF NOT EXISTS reservation.reservation
(
    ID             bigint unsigned auto_increment
        primary key,
    checked_in     bit                                 null,
    NUMBER_OF_BAGS int                                 null,
    PASSENGER_ID   bigint unsigned                     not null,
    FLIGHT_ID      bigint unsigned                     not null,
    CREATED        timestamp default CURRENT_TIMESTAMP null,
    constraint ID
        unique (ID),
    constraint reservation_ibfk_1
        foreign key (PASSENGER_ID) references reservation.passenger (ID)
            on delete cascade,
    constraint reservation_ibfk_2
        foreign key (FLIGHT_ID) references reservation.flight (ID)
);

create table IF NOT EXISTS reservation.passenger
(
    ID          bigint unsigned auto_increment
        primary key,
    first_name  varchar(255) null,
    last_name   varchar(255) null,
    middle_name varchar(255) null,
    email       varchar(255) null,
    phone       varchar(255) null,
    constraint ID
        unique (ID)
);

create table IF NOT EXISTS reservation.reservation
(
    ID             bigint unsigned auto_increment
        primary key,
    checked_in     bit                                 null,
    NUMBER_OF_BAGS int                                 null,
    PASSENGER_ID   bigint unsigned                     not null,
    FLIGHT_ID      bigint unsigned                     not null,
    CREATED        timestamp default CURRENT_TIMESTAMP null,
    constraint ID
        unique (ID),
    constraint reservation_ibfk_1
        foreign key (PASSENGER_ID) references reservation.passenger (ID)
            on delete cascade,
    constraint reservation_ibfk_2
        foreign key (FLIGHT_ID) references reservation.flight (ID)
);


CREATE DATABASE IF NOT EXISTS PROJECTDB;

create table IF NOT EXISTS projectdb.location
(
    id   int          not null
        primary key,
    code varchar(255) null,
    name varchar(255) null,
    type varchar(255) null
);

create table IF NOT EXISTS projectdb.document
(
	id BIGINT NOT NULL,
	name VARCHAR(100) NOT NULL,
	data BLOB NOT NULL,
	PRIMARY KEY (id)
);


CREATE DATABASE IF NOT EXISTS PROJECTDB;

create table IF NOT EXISTS projectdb.studenttab
(
    id      bigint       not null
        primary key,
    scourse varchar(255) null,
    sfee    double       null,
    sname   varchar(255) null
);

--create table IF NOT EXISTS projectdb.studenttab_seq
--(
--    next_val bigint null
--);



--create index IF NOT EXISTS FLIGHT_ID
--    on reservation.reservation (FLIGHT_ID);
--
--create index IF NOT EXISTS PASSENGER_ID
--    on reservation.reservation (PASSENGER_ID);
--    
--create index IF NOT EXISTS role_id
--    on reservation.user_role (role_id);
--
--create index user_id
--    on reservation.user_role (user_id);
--    
--create table IF NOT EXISTS reservation.passenger_seq
--(
--    next_val bigint null
--);
--
--create table IF NOT EXISTS reservation.user_seq
--(
--    next_val bigint null
--);
--
--create table IF NOT EXISTS reservation.reservation_seq
--(
--    next_val bigint null
--);
--
--create table IF NOT EXISTS reservation.passenger_seq
--(
--    next_val bigint null
--);
--
--create table IF NOT EXISTS reservation.flight_seq
--(
--    next_val bigint null
--);