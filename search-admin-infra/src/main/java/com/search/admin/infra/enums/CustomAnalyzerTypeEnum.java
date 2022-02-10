package com.search.admin.infra.enums;

public enum CustomAnalyzerTypeEnum implements KeyValueEnum<String>{
    ;

    private String code;
    private String desc;

    CustomAnalyzerTypeEnum(String code, String desc) {
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
