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

delete from ms_menu where menu_position = 'admin';
INSERT INTO ms_menu
(menu_position, menu_level, menu_name, menu_path, menu_summary, menu_order)
VALUES
('admin', 1, '首页', '/ms', '首页', 1000),
('admin', 1, '系统', '', '系统', 9000),
('admin', 2, '参数', '/ms/meta/list', '参数', 9010),
('admin', 2, '菜单', '/ms/menu/list', '参数', 9100),
('admin', 2, '用户', '/ms/user/list', '参数', 9110),
('admin', 2, '任务', '/ms/job/list', '任务', 9900),
('admin', 2, '任务记录', '/ms/job/record', '任务记录', 9910);
