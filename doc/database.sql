create database search;
create user search_admin with password 'search_admin';
grant all privileges on database search to search_admin;


创建index_settings表
-- auto-generated definition
create table index_settings
(
    id            varchar(128) not null
        constraint index_settings_pk
        primary key,
    index_name    varchar(255) not null,
    index_mapping json,
    creator       varchar(64)  not null,
    create_time   varchar(14)  not null,
    modifier      varchar(64)  not null,
    modify_time   varchar(14)  not null,
    delete_flag   varchar(2)   not null,
    settings      json,
    index_desc    varchar(12)
);

comment on table index_settings is '索引信息表';

comment on column index_settings.id is '主键';

comment on column index_settings.index_name is '索引名称';

comment on column index_settings.index_mapping is '字段映射';

comment on column index_settings.creator is '创建者';

comment on column index_settings.create_time is '创建时间';

comment on column index_settings.modifier is '修改者';

comment on column index_settings.modify_time is '修改时间';

comment on column index_settings.delete_flag is '是否有效 0有效1无效';

comment on column index_settings.settings is '索引的setting设置';

alter table index_settings
    owner to search_admin;

create index idx_index_name
    on index_settings (index_name);



