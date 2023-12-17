CREATE DATABASE RESERVATION;

USE RESERVATION;

/* -- https://www.tutorialspoint.com/In-MySQL-what-is-the-difference-between-SERIAL-and-AUTO-INCREMENT -- */


CREATE TABLE USERS
(
    ID SERIAL,
    FIRST_NAME VARCHAR(20),
    LAST_NAME VARCHAR(20),
    EMAIL VARCHAR(20),
    PASSWORD VARCHAR(256),
    PRIMARY KEY (ID),
    UNIQUE KEY (EMAIL)
);

CREATE TABLE PASSENGER
(
    ID   SERIAL NOT NULL,
    FIRST_NAME       VARCHAR(256),
    LAST_NAME    VARCHAR(256),
    MIDDLE_NAME   VARCHAR(256),
    EMAIL VARCHAR(50),
    PHONE VARCHAR(10),
    PRIMARY KEY (ID)
);


CREATE TABLE FLIGHT
(
    ID SERIAL  NOT NULL,
    FLIGHT_NUMBER VARCHAR(20)  NOT NULL,
    OPERATING_AIRLINES VARCHAR(20)  NOT NULL,
    DEPARTURE_CITY VARCHAR(20)  NOT NULL,
    ARRIVAL_CITY VARCHAR(20)  NOT NULL,
    DATE_OF_DEPARTURE DATE  NOT NULL,
    ESTIMATED_DEPARTURE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
);


-- create table flight
-- (
--     ID                       bigint unsigned auto_increment
--         primary key,
--     flight_number            varchar(255)                        null,
--     operating_airlines       varchar(255)                        null,
--     departure_city           varchar(255)                        null,
--     arrival_city             varchar(255)                        null,
--     date_of_departure        date                                null,
--     ESTIMATED_DEPARTURE_TIME timestamp default CURRENT_TIMESTAMP null,
--     estimate_departure_time  datetime(6)                         null,
--     constraint ID
--         unique (ID)
-- );


/* This is for forign key and reference https://www.tutorialspoint.com/how-to-get-the-datatype-of-mysql-table-columns */
select data_type from information_schema.columns where table_schema = 'RESERVATION' and table_name = 'FLIGHT';
DESCRIBE PASSENGER;
show table STATUS like 'PASSENGER';


CREATE TABLE RESERVATION
(
    ID SERIAL NOT NULL,
    CHECKED_IN INT,
    NUMBER_OF_BAGS INT,
    PASSENGER_ID bigint unsigned NOT NULL,      /* Check the data type of passenger "DESCRIBE PASSENGER;" */
    FLIGHT_ID bigint unsigned NOT NULL,         /* Check the data type of FLIGHT "DESCRIBE FLIGHT;" */
    CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID),
    FOREIGN KEY (PASSENGER_ID) REFERENCES PASSENGER(ID) ON DELETE CASCADE,
    FOREIGN KEY (FLIGHT_ID) REFERENCES FLIGHT(ID)
);

SELECT * FROM USERS;

SELECT * FROM PASSENGER;

SELECT * FROM FLIGHT;

SELECT * FROM RESERVATION;