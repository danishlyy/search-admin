package com.search.admin.infra.enums;

public enum DictionaryTypeEnum implements KeyValueEnum<String>{
    ANALYZER_TYPE("1","分词器类型"),
    FIELD_TYPE("2","字段类型"),
    YES_NO_TYPE("3","是否类型"),;


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
