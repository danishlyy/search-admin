package com.search.admin.infra.enums;

public enum YesOrNoEnum implements KeyValueEnum<String>{
    TYPE_YES("0","是"),
    TYPE_NO("1","否"),;

    private String code;
    private String desc;

    YesOrNoEnum(String code, String desc) {
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
