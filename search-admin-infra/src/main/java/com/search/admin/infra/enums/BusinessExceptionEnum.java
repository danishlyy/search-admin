package com.search.admin.infra.enums;

public enum BusinessExceptionEnum implements KeyValueEnum {
    INDEX_NAME_EXIST("10000001", "indexName %s has exist"),
    INDEX_SETTING_CONVERT("10000002", "index setting convert2json or json2object failed"),
    INDEX_NOT_EXIST("10000003", "index not exist"),
    NUMBER_OF_REPLICAS_ILLEGAL("10000004", "index replicas must more than 1"),
    INDEX_SETTING_EMPTY("10000005", "index setting is empty"),
    CLUSTER_HEALTH_FAILED("10000006", "cannot watch cluster health "),
    CREATE_INDEX_FAILED("10000007", "sync index info to elasticsearch failed "),
    DICTIONARY_TYPE_IS_NULL("10000008", "dictionary type is null "),
    DICTIONARY_CODE_REPEATABLE("10000009", "dictType_dictCode %s  is repeatable "),
    ANALYZE_TEXT_FAILED("10000010", "analyze failed "),
    PARSE_MAPPING_OBJECT_FAILED("10000011", "parse mapping object failed "),
    INDEX_DELETE_FAILED("10000012", "delete index failed "),
    CHECK_SNAPSHOT_REPOSITORY_FAILED("10000013", "check snapshot repository failed"),
    CREATE_SNAPSHOT_REPOSITORY_FAILED("10000014", "create snapshot repository failed"),
    CREATE_INDEX_SNAPSHOT_FAILED("10000015", "create index snapshot failed"),
    RECOVER_INDEX_SNAPSHOT_FAILED("10000016", "recover index snapshot failed"),
    DELETE_INDEX_SNAPSHOT_FAILED("10000017", "delete index snapshot failed"),
    INDEX_FIELD_REPEATED("10000018", "index field repeated"),
    REINDEX_FAILED("10000019", "reindex failed"),
    SYSTEM_ERROR("10000099", "system error "),
    ;

    private String code;
    private String desc;

    BusinessExceptionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
