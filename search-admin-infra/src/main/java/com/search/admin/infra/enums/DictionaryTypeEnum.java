package com.search.admin.infra.enums;

public enum DictionaryTypeEnum implements KeyValueEnum<String>{
    ANALYZER_TYPE("1","分词器类型"),
    FIELD_TYPE("2","字段类型"),
    YES_NO_TYPE("3","是否类型"),
    AUDIT_TYPE("4","审核类型"),
    EFFECTIVE_TYPE("5","有效无效类型"),
    SYNC_TYPE("6","同步类型"),
    SYNC_STATUS_TYPE("7","同步状态类型"),

    ;


    private String code;
    private String desc;

    DictionaryTypeEnum(String code, String desc) {
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
