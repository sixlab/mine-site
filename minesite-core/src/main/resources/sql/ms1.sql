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
