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
    index_mapping      text,
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
create table audit_index_info
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
    delete_flag       varchar(2)   not null,
    index_name        varchar(255),
    audit_type        varchar(2),
    index_status      varchar(2),
    reindex_status    varchar(2),
    notice_time       varchar(14),
    reindex_flag      varchar(2)
);

comment on table audit_index_info is '同步es索引信息历史表';

comment on column audit_index_info.id is '主键';

comment on column audit_index_info.index_settings_id is '索引id';

comment on column audit_index_info.sync_type is '同步类型：0 setting同步 1 mapping同步 2 全部';

comment on column audit_index_info.sync_status is '同步结果1 已同步 0 未同步';

comment on column audit_index_info.creator is '创建者';

comment on column audit_index_info.create_time is '创建时间';

comment on column audit_index_info.modifier is '修改人';

comment on column audit_index_info.modify_time is '修改时间';

comment on column audit_index_info.delete_flag is '是否有效 0有效 1无效';

comment on column audit_index_info.index_name is '索引名称';

comment on column audit_index_info.audit_type is '审核类型1已审核0待审核';

comment on column audit_index_info.index_status is '索引状态0有效 1无效';

comment on column audit_index_info.reindex_status is 'reindex成功标志0成功1失败';

comment on column audit_index_info.notice_time is '通知审核时间';

comment on column audit_index_info.reindex_flag is 'reindex标志 0是 1否 ';

alter table audit_index_info
    owner to search_admin;







