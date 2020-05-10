-- auto-generated definition
create table ms_user
(
    id          int auto_increment
        primary key,
    username    varchar(30)  null,
    nickname    varchar(30)  null,
    password    varchar(100) null,
    status      int          null,
    create_time datetime(6)  null,
    role        varchar(10)  null,
    constraint UKg654enkj2gifxv3miea4v46w7
        unique (username)
);

