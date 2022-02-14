package com.search.admin.infra.enums;

public enum AuditTypeEnum implements KeyValueEnum<String>{
    WAIT_AUDIT("0","待审核"),
    AUDIT_PASS("1","审核通过"),
    AUDIT_REJECT("2","拒绝审核"),;

    private String code;
    private String desc;

    AuditTypeEnum(String code, String desc) {
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
