-- we don't know how to generate root <with-no-name> (class Root) :(
grant audit_abort_exempt, firewall_exempt, select, system_user on *.* to 'mysql.infoschema'@localhost;

grant audit_abort_exempt, authentication_policy_admin, backup_admin, clone_admin, connection_admin, firewall_exempt, persist_ro_variables_admin, session_variables_admin, shutdown, super, system_user, system_variables_admin on *.* to 'mysql.session'@localhost;

grant audit_abort_exempt, firewall_exempt, system_user on *.* to 'mysql.sys'@localhost;

grant alter, alter routine, application_password_admin, audit_abort_exempt, audit_admin, authentication_policy_admin, backup_admin, binlog_admin, binlog_encryption_admin, clone_admin, connection_admin, create, create role, create routine, create tablespace, create temporary tables, create user, create view, delete, drop, drop role, encryption_key_admin, event, execute, file, firewall_exempt, flush_optimizer_costs, flush_status, flush_tables, flush_user_resources, group_replication_admin, group_replication_stream, index, innodb_redo_log_archive, innodb_redo_log_enable, insert, lock tables, passwordless_user_admin, persist_ro_variables_admin, process, references, reload, replication client, replication slave, replication_applier, replication_slave_admin, resource_group_admin, resource_group_user, role_admin, select, sensitive_variables_observer, service_connection_admin, session_variables_admin, set_user_id, show databases, show view, show_routine, shutdown, super, system_user, system_variables_admin, table_encryption_admin, trigger, update, xa_recover_admin, grant option on *.* to root@localhost;

grant alter, alter routine, create, create routine, create tablespace, create temporary tables, create user, create view, delete, drop, event, execute, file, index, insert, lock tables, process, references, reload, replication client, replication slave, select, show databases, show view, shutdown, super, trigger, update, grant option on *.* to sarath69kumar;

create table university.address
(
    id     int auto_increment
        primary key,
    street varchar(100) not null,
    city   varchar(45)  not null
)
    charset = latin1;

create table reservation.flight
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

create table reservation.flight_seq
(
    next_val bigint null
);

create table projectdb.location
(
    id   int          not null
        primary key,
    code varchar(255) null,
    name varchar(255) null,
    type varchar(255) null
);

create table reservation.passenger
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

create table reservation.passenger_seq
(
    next_val bigint null
);

create table reservation.reservation
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

create index FLIGHT_ID
    on reservation.reservation (FLIGHT_ID);

create index PASSENGER_ID
    on reservation.reservation (PASSENGER_ID);

create table reservation.reservation_seq
(
    next_val bigint null
);

create table reservation.role
(
    ID   int auto_increment
        primary key,
    NAME varchar(20) null
);

create table university.student
(
    id         int auto_increment
        primary key,
    first_name varchar(50) not null,
    last_name  varchar(50) null,
    email      varchar(30) null,
    address_id int         null
)
    charset = latin1;

create table projectdb.studenttab
(
    id      bigint       not null
        primary key,
    scourse varchar(255) null,
    sfee    double       null,
    sname   varchar(255) null
);

create table projectdb.studenttab_seq
(
    next_val bigint null
);

create table reservation.user
(
    id         bigint       not null
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    password   varchar(255) null
);

create table reservation.user_role
(
    role_id int    null,
    user_id bigint not null,
    constraint user_role_ibfk_1
        foreign key (user_id) references reservation.user (id),
    constraint user_role_ibfk_2
        foreign key (role_id) references reservation.role (ID)
);

create index role_id
    on reservation.user_role (role_id);

create index user_id
    on reservation.user_role (user_id);

create table reservation.user_seq
(
    next_val bigint null
);

create table reservation.users
(
    ID         bigint unsigned auto_increment
        primary key,
    FIRST_NAME varchar(20)  null,
    LAST_NAME  varchar(20)  null,
    EMAIL      varchar(20)  null,
    PASSWORD   varchar(256) null,
    constraint EMAIL
        unique (EMAIL),
    constraint ID
        unique (ID)
);

