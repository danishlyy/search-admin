package com.search.admin.infra.enums;

public enum BusinessExceptionEnum implements KeyValueEnum{
    index_name_exist("10000001","indexName has exist");

    private String code;
    private String desc;

    BusinessExceptionEnum(String code, String desc) {
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
