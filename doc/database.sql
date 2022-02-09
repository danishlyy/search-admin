create database search;
create user search_admin with password 'search_admin';
grant all privileges on database search to search_admin;


-- auto-generated definition
create table index_settings
(
    id                 varchar(128) not null
        constraint index_settings_pk
        primary key,
    index_name         varchar(255) not null,
    index_mapping      json,
    creator            varchar(64)  not null,
    create_time        varchar(14)  not null,
    modifier           varchar(64)  not null,
    modify_time        varchar(14)  not null,
    delete_flag        varchar(2)   not null,
    index_desc         varchar(12),
    number_of_replicas varchar(2),
    number_of_shards   varchar(2)
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

comment on column index_settings.number_of_replicas is '副本数';

comment on column index_settings.number_of_shards is '分片数';

alter table index_settings
    owner to search_admin;

create index idx_index_name
    on index_settings (index_name);

-- auto-generated definition
create table search_dictionary
(
    id          varchar(128) not null
        constraint search_dictionary_pk
        primary key,
    dict_type   varchar(16),
    dict_code   varchar(16),
    dict_value  varchar(64),
    creator     varchar(64)  not null,
    create_time varchar(14)  not null,
    modifier    varchar(64)  not null,
    modify_time varchar(14)  not null,
    delete_flag varchar(2)   not null,
    dict_desc   varchar(256)
);

comment on table search_dictionary is '搜索字典表';

comment on column search_dictionary.id is '主键';

comment on column search_dictionary.dict_type is '字典类型';

comment on column search_dictionary.dict_code is '字典代码';

comment on column search_dictionary.dict_value is '字典值';

comment on column search_dictionary.creator is '创建者';

comment on column search_dictionary.create_time is '创建时间';

comment on column search_dictionary.modifier is '修改者';

comment on column search_dictionary.modify_time is '修改时间';

comment on column search_dictionary.delete_flag is '是否有效0有效1无效';

comment on column search_dictionary.dict_desc is '字典描述';

alter table search_dictionary
    owner to search_admin;

create index idx_type_code
    on search_dictionary (dict_code, dict_type);



-- auto-generated definition
create table sync_index_info_history
(
    id                varchar(128) not null
        constraint sync_index_info_history_pk
        primary key,
    index_settings_id varchar(128) not null,
    sync_type         varchar(2)   not null,
    sync_status       varchar(2),
    creator           varchar(64)  not null,
    create_time       varchar(14)  not null,
    modifier          varchar(64)  not null,
    modify_time       varchar(14)  not null,
    delete_flag       varchar(2)   not null
);

comment on table sync_index_info_history is '同步es索引信息历史表';

comment on column sync_index_info_history.id is '主键';

comment on column sync_index_info_history.index_settings_id is '索引id';

comment on column sync_index_info_history.sync_type is '同步类型：0 setting同步 1 mapping同步';

comment on column sync_index_info_history.sync_status is '同步状态1 已同步 0 未同步';

comment on column sync_index_info_history.creator is '创建者';

comment on column sync_index_info_history.create_time is '创建时间';

comment on column sync_index_info_history.modifier is '修改人';

comment on column sync_index_info_history.modify_time is '修改时间';

comment on column sync_index_info_history.delete_flag is '是否有效 0有效 1无效';

alter table sync_index_info_history
    owner to search_admin;





