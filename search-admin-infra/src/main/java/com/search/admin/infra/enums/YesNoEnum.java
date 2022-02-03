package com.search.admin.infra.enums;

public enum YesNoEnum implements KeyValueEnum<String> {
    YES("0", "有效"),
    NO("0", "无效");

    private String code;
    private String desc;

    YesNoEnum(String code, String desc) {
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
