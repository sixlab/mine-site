create table ms_job
(
    id int auto_increment
        primary key,
    job_clz varchar(30) null,
    job_name varchar(30) null,
    status int null,
    last_time datetime null comment '上次运行时间',
    last_status int null comment '状态：0-失败，1-正常',
    create_time datetime(6) null
);
create table ms_job_record
(
    id int auto_increment
        primary key,
    job_id int null,
    job_name varchar(30) null,
    msg longtext null,
    status int null,
    create_time datetime(6) null
);
create table ms_user
(
    id int auto_increment
        primary key,
    username varchar(30) null,
    nickname varchar(30) null,
    password varchar(100) null,
    role varchar(10) null,
    status int null,
    token varchar(50) null,
    login_time datetime null,
    expire_time datetime null,
    create_time datetime(6) null,
    constraint UKg654enkj2gifxv3miea4v46w7
        unique (username)
);
create index ms_user_role_index
    on ms_user (role);

INSERT INTO ms_meta (meta_group, meta_key, meta_val)
VALUES
       ('six-siteInfo', 'siteName', '网站名称'),
       ('six-siteInfo', 'titleSeparator', '|'),
       ('six-siteInfo', 'keywords', 'sixlab'),
       ('six-siteInfo', 'description', 'sixlab.cn');