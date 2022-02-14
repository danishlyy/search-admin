package com.search.admin.infra.enums;

public enum IndexStatusEnum implements KeyValueEnum<String>{
    INDEX_EFFECTIVE("0","有效"),
    INDEX_INEFFECTIVE("1","无效"),;


    private String code;
    private String desc;

    IndexStatusEnum(String code, String desc) {
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
