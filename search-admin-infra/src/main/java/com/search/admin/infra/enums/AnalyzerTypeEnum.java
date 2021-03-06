package com.search.admin.infra.enums;

public enum AnalyzerTypeEnum implements KeyValueEnum<String>{
    INNER_ANALYZER_TYPE("1","ES内置分词器"),
    CUSTOM_ANALYZER_TYPE("2","自定义分词器"),;

    private String code;
    private String desc;

    AnalyzerTypeEnum(String code, String desc) {
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
