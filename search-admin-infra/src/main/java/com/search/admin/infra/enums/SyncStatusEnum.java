package com.search.admin.infra.enums;

public enum SyncStatusEnum implements KeyValueEnum<String>{
    WAIT_SYNC("0","待同步"),
    SYNC_SUCCESS("1","同步成功"),
    SYNC_FAILURE("2","同步失败"),;

    private String code;
    private String desc;

    SyncStatusEnum(String code, String desc) {
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
