package com.search.admin.infra.enums;

public enum DictionaryTypeEnum implements KeyValueEnum<String>{
    analyzer_type("1","分词器类型"),
    field_type("2","字段类型"),;


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
