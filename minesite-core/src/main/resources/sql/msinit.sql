create table if not exists QRTZ_CALENDARS
(
    SCHED_NAME varchar(120) not null,
    CALENDAR_NAME varchar(200) not null,
    CALENDAR blob not null,
    primary key (SCHED_NAME, CALENDAR_NAME)
);

create table if not exists QRTZ_FIRED_TRIGGERS
(
    SCHED_NAME varchar(120) not null,
    ENTRY_ID varchar(95) not null,
    TRIGGER_NAME varchar(200) not null,
    TRIGGER_GROUP varchar(200) not null,
    INSTANCE_NAME varchar(200) not null,
    FIRED_TIME bigint(13) not null,
    SCHED_TIME bigint(13) not null,
    PRIORITY int not null,
    STATE varchar(16) not null,
    JOB_NAME varchar(200) null,
    JOB_GROUP varchar(200) null,
    IS_NONCONCURRENT varchar(1) null,
    REQUESTS_RECOVERY varchar(1) null,
    primary key (SCHED_NAME, ENTRY_ID)
);

create table if not exists QRTZ_JOB_DETAILS
(
    SCHED_NAME varchar(120) not null,
    JOB_NAME varchar(200) not null,
    JOB_GROUP varchar(200) not null,
    DESCRIPTION varchar(250) null,
    JOB_CLASS_NAME varchar(250) not null,
    IS_DURABLE varchar(1) not null,
    IS_NONCONCURRENT varchar(1) not null,
    IS_UPDATE_DATA varchar(1) not null,
    REQUESTS_RECOVERY varchar(1) not null,
    JOB_DATA blob null,
    primary key (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

create table if not exists QRTZ_LOCKS
(
    SCHED_NAME varchar(120) not null,
    LOCK_NAME varchar(40) not null,
    primary key (SCHED_NAME, LOCK_NAME)
);

create table if not exists QRTZ_PAUSED_TRIGGER_GRPS
(
    SCHED_NAME varchar(120) not null,
    TRIGGER_GROUP varchar(200) not null,
    primary key (SCHED_NAME, TRIGGER_GROUP)
);

create table if not exists QRTZ_SCHEDULER_STATE
(
    SCHED_NAME varchar(120) not null,
    INSTANCE_NAME varchar(200) not null,
    LAST_CHECKIN_TIME bigint(13) not null,
    CHECKIN_INTERVAL bigint(13) not null,
    primary key (SCHED_NAME, INSTANCE_NAME)
);

create table if not exists QRTZ_TRIGGERS
(
    SCHED_NAME varchar(120) not null,
    TRIGGER_NAME varchar(200) not null,
    TRIGGER_GROUP varchar(200) not null,
    JOB_NAME varchar(200) not null,
    JOB_GROUP varchar(200) not null,
    DESCRIPTION varchar(250) null,
    NEXT_FIRE_TIME bigint(13) null,
    PREV_FIRE_TIME bigint(13) null,
    PRIORITY int null,
    TRIGGER_STATE varchar(16) not null,
    TRIGGER_TYPE varchar(8) not null,
    START_TIME bigint(13) not null,
    END_TIME bigint(13) null,
    CALENDAR_NAME varchar(200) null,
    MISFIRE_INSTR smallint(2) null,
    JOB_DATA blob null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint QRTZ_TRIGGERS_ibfk_1
        foreign key (SCHED_NAME, JOB_NAME, JOB_GROUP) references QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

create table if not exists QRTZ_BLOB_TRIGGERS
(
    SCHED_NAME varchar(120) not null,
    TRIGGER_NAME varchar(200) not null,
    TRIGGER_GROUP varchar(200) not null,
    BLOB_DATA blob null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint QRTZ_BLOB_TRIGGERS_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table if not exists QRTZ_CRON_TRIGGERS
(
    SCHED_NAME varchar(120) not null,
    TRIGGER_NAME varchar(200) not null,
    TRIGGER_GROUP varchar(200) not null,
    CRON_EXPRESSION varchar(200) not null,
    TIME_ZONE_ID varchar(80) null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint QRTZ_CRON_TRIGGERS_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table if not exists QRTZ_SIMPLE_TRIGGERS
(
    SCHED_NAME varchar(120) not null,
    TRIGGER_NAME varchar(200) not null,
    TRIGGER_GROUP varchar(200) not null,
    REPEAT_COUNT bigint(7) not null,
    REPEAT_INTERVAL bigint(12) not null,
    TIMES_TRIGGERED bigint(10) not null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint QRTZ_SIMPLE_TRIGGERS_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create table if not exists QRTZ_SIMPROP_TRIGGERS
(
    SCHED_NAME varchar(120) not null,
    TRIGGER_NAME varchar(200) not null,
    TRIGGER_GROUP varchar(200) not null,
    STR_PROP_1 varchar(512) null,
    STR_PROP_2 varchar(512) null,
    STR_PROP_3 varchar(512) null,
    INT_PROP_1 int null,
    INT_PROP_2 int null,
    LONG_PROP_1 bigint null,
    LONG_PROP_2 bigint null,
    DEC_PROP_1 decimal(13,4) null,
    DEC_PROP_2 decimal(13,4) null,
    BOOL_PROP_1 varchar(1) null,
    BOOL_PROP_2 varchar(1) null,
    primary key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
    constraint QRTZ_SIMPROP_TRIGGERS_ibfk_1
        foreign key (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) references QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

create index SCHED_NAME
    on QRTZ_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);

create table ms_data
(
    id int auto_increment
        primary key,
    user_id int null comment '用户id',
    data_type varchar(30) null comment '数据类型',
    data_id varchar(100) null comment '数据标识',
    data json null comment '上次运行时间',
    create_time datetime(6) null
)
    comment 'json table 数据表';

create index ms_data_user_id_data_type_data_id_index
    on ms_data (user_id, data_type, data_id);

create index ms_data_user_id_data_type_index
    on ms_data (user_id, data_type);

create index ms_data_user_id_index
    on ms_data (user_id);

create table if not exists ms_job
(
    id int auto_increment
        primary key,
    job_code varchar(20) null,
    job_clz varchar(30) null,
    job_method varchar(20) null,
    job_name varchar(30) null,
    status int null,
    last_time datetime null comment '上次运行时间',
    last_status int null comment '状态：0-失败，1-正常',
    create_time datetime(6) null
);

create table if not exists ms_job_record
(
    id int auto_increment
        primary key,
    job_id int null,
    job_name varchar(30) null,
    msg longtext null,
    status int null,
    create_time datetime(6) null
);

create table if not exists ms_menu
(
    id int auto_increment
        primary key,
    menu_position varchar(20) null,
    menu_level int null,
    menu_name varchar(20) null,
    menu_path varchar(200) null,
    menu_summary varchar(1000) null,
    menu_order int null,
    create_time datetime null
)
    comment '站点菜单';

create index ms_menu_menu_position_index
    on ms_menu (menu_position);

create table if not exists ms_meta
(
    id int auto_increment
        primary key,
    fk_id int null,
    meta_group varchar(200) null,
    meta_key varchar(200) null,
    meta_val longtext null,
    remark varchar(1000) null,
    create_time datetime(6) null
);

create table if not exists ms_user
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
('six-siteInfo', 'siteLogo', '/static/images/ms.png'),
('six-siteInfo', 'titleSeparator', '|'),
('six-siteInfo', 'keywords', 'minesoft'),
('six-siteInfo', 'description', 'minesoft.tech');

INSERT INTO ms_user (username, nickname, password, role, status)
VALUES
('admax', '管理员', '$2a$10$s/xO98MdVefRAl1Yj05aROXUxoUCCOnAW/Uf/DmTYMA1XC0MIbbBG', 'admin', '1');

INSERT INTO ms_menu
(menu_position, menu_level, menu_name, menu_path, menu_summary, menu_order)
VALUES
('admin', 1, '首页', '/ms', '首页', 1000),
('admin', 1, '系统', '', '系统', 9000),
('admin', 2, '参数', '/ms/meta/list', '参数', 9010),
('admin', 2, '菜单', '/ms/menu/list', '参数', 9100),
('admin', 2, '用户', '/ms/user/list', '参数', 9110),
('admin', 2, '任务', '/ms/job/list', '任务', 9900),
('admin', 2, '运行任务', '/ms/job/active', '运行任务', 9910);
