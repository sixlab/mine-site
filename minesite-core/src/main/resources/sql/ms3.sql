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

