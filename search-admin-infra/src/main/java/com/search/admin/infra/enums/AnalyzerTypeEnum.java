package com.search.admin.infra.enums;

public enum AnalyzerTypeEnum implements KeyValueEnum<String>{
    analyzer_type_standard("standard","standard"),
    analyzer_type_simple("simple","simple"),
    analyzer_type_whitespace("whitespace","whitespace"),
    analyzer_type_stop("stop","stop"),
    analyzer_type_keyword("keyword","keyword"),
    analyzer_type_pattern("pattern","pattern"),;


    private String code;
    private String desc;

    AnalyzerTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }
}
