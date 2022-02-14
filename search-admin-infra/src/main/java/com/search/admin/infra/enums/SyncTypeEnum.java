package com.search.admin.infra.enums;

public enum SyncTypeEnum implements KeyValueEnum<String>{
    SYNC_SETTING("0","setting同步"),
    SYNC_MAPPING("1","mapping同步"),
    SYNC_ALL("2","全部同步"),;

    private String code;
    private String desc;

    SyncTypeEnum(String code, String desc) {
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
