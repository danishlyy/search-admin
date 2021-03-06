package com.search.admin.infra.enums;

public enum ElasticSearchClusterEnum implements KeyValueEnum<String>{
    GREEN("GREEN","GREEN"),
    YELLOW("YELLOW","YELLOW"),
    RED("RED","RED"),;

    private String code;
    private String desc;

    ElasticSearchClusterEnum(String code, String desc) {
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
