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

create table vod_group
(
    id          int auto_increment
        primary key,
    group_name  varchar(20)   null comment '分类名',
    status      int default 0 not null comment '0-禁用，1-启用',
    create_time datetime(6)   null comment '创建时间'
)
    comment '分类';

create index vod_group_group_name_index
    on vod_group (group_name);

create table vod_info
(
    id           int auto_increment
        primary key,
    vod_name     varchar(10)  null comment '名字',
    vod_group    varchar(20)  null comment '分组',
    vod_pic      varchar(10)  null comment '图片',
    vod_area     varchar(20)  null comment '区域',
    vod_lang     varchar(10)  null comment '语言',
    vod_year     varchar(10)  null comment '年份',
    vod_actor    varchar(255) null comment '演员',
    vod_director varchar(255) null comment '导演',
    vod_intro    text         null comment '简介',
    update_time  datetime(6)  null comment '更新时间',
    create_time  datetime(6)  null comment '创建时间'
)
    comment '数据';

create index vod_info_vod_group_index
    on vod_info (vod_group);

create index vod_info_vod_group_vod_year_index
    on vod_info (vod_group, vod_year);

create index vod_info_vod_name_vod_year_vod_director_index
    on vod_info (vod_name, vod_year, vod_director);

create table vod_info_urls
(
    id          int auto_increment
        primary key,
    info_id     int           null comment 'vod_info 的 id',
    player_code varchar(20)   null comment '播放器code',
    player_name varchar(20)   null comment '播放器名字',
    vod_remarks varchar(100)  null comment '进度标记',
    vod_urls    text          null comment '链接字符串，ep1$link1#ep2$link2',
    status      int default 0 not null comment '状态,0-禁用，1-启用',
    create_time datetime(6)   null comment '创建时间'
)
    comment '数据';

create index vod_info_urls_info_id_index
    on vod_info_urls (info_id);

create index vod_info_urls_info_id_player_code_index
    on vod_info_urls (info_id, player_code);

create index vod_info_urls_player_code_index
    on vod_info_urls (player_code);

create table vod_player
(
    id          int auto_increment
        primary key,
    site_code   varchar(10)   null comment '配置code',
    player_code varchar(20)   null comment '播放器code',
    player_name varchar(20)   null comment '播放器名字',
    status      int default 0 not null comment '0-禁用，1-启用',
    create_time datetime(6)   null comment '创建时间'
)
    comment '播放器';

create index vod_player_player_code_index
    on vod_player (player_code);

create index vod_player_player_code_site_code_index
    on vod_player (player_code, site_code);

create table vod_site
(
    id           int auto_increment
        primary key,
    site_name    varchar(10)   null comment '配置名字',
    site_code    varchar(10)   null comment '配置code，唯一',
    site_type    varchar(10)   null comment '配置类型',
    site_url     varchar(200)  null comment '配置url',
    status       int default 0 not null comment '状态：0-禁用，1-启用',
    page         int default 0 not null comment '正在处理的页码，0：完成',
    begin_time   datetime(6)   null comment '上次开始时间',
    end_time     datetime(6)   null comment '上次完成时间',
    group_relate text          null comment '分组映射',
    create_time  datetime(6)   null comment '创建时间',
    constraint vod_site_site_code_uindex
        unique (site_code)
)
    comment '数据源配置';
