create schema minesite collate utf8mb4_general_ci;

create table ms_data
(
    id          int auto_increment
        primary key,
    code        varchar(255)   null,
    data        decimal(18, 4) null,
    text        varchar(255)   null,
    status      int            null,
    create_time datetime(6)    null
);

create index ms_data_code_index
    on ms_data (code);

create table ms_notify_config
(
    id          int auto_increment
        primary key,
    code        varchar(255)   null,
    type        int            null,
    rise        decimal(18, 4) null,
    status      int            null,
    create_time datetime(6)    null
);

create index ms_notify_config_code_index
    on ms_notify_config (code);

create index ms_notify_config_type_index
    on ms_notify_config (type);

create table ms_user
(
    id          int auto_increment
        primary key,
    username    varchar(30)  null,
    nickname    varchar(30)  null,
    password    varchar(100) null,
    role        varchar(10)  null,
    status      int          null,
    create_time datetime(6)  null,
    constraint UKg654enkj2gifxv3miea4v46w7
        unique (username)
);

create index ms_user_role_index
    on ms_user (role);

