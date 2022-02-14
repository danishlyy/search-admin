package com.search.admin.infra.enums;

public enum InnerAnalyzerTypeEnum implements KeyValueEnum<String>{
    ANALYZER_TYPE_STANDARD("standard","standard"),
    ANALYZER_TYPE_SIMPLE("simple","simple"),
    ANALYZER_TYPE_WHITESPACE("whitespace","whitespace"),
    ANALYZER_TYPE_STOP("stop","stop"),
    ANALYZER_TYPE_KEYWORD("keyword","keyword"),
    ANALYZER_TYPE_PATTERN("pattern","pattern"),;


    private String code;
    private String desc;

    InnerAnalyzerTypeEnum(String code, String desc) {
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
