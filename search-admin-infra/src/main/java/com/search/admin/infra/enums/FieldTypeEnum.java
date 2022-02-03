package com.search.admin.infra.enums;

public enum FieldTypeEnum implements KeyValueEnum {
    TYPE_ARRAY("array", "array"),
    TYPE_BOOLEAN("boolean", "boolean"),
    TYPE_DATE("date", "date"),
    TYPE_DATE_NANOS("date_nanos", "date_nanos"),
    TYPE_KEYWORD("keyword", "keyword"),
    TYPE_NESTED("nested", "nested"),
    TYPE_LONG("long", "long"),
    TYPE_SHORT("short", "short"),
    TYPE_BYTE("byte", "byte"),
    TYPE_DOUBLE("double", "double"),
    TYPE_FLOAT("float", "float"),
    TYPE_OBJECT("object", "object"),
    TYPE_TEXT("text", "text"),
    ;

    private String code;
    private String desc;

    @Override
    public String getCode() {
        return this.code;
    }

    FieldTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
