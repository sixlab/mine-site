create table if not exists ms_data
(
    id          int auto_increment
        primary key,
    data_type varchar(30) null comment '数据类型',
    data        json        null comment '上次运行时间',
    create_time datetime(6) null
)
    comment 'json table 数据表';

create index ms_data_data_type_index
    on ms_data (data_type);
