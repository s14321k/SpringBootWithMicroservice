create table if not exists projectdb.studenttab
(
    id      bigint       not null
    primary key,
    scourse varchar(255) null,
    sfee    double       null,
    sname   varchar(255) null
);

create view new_view as
select *
from studenttab;