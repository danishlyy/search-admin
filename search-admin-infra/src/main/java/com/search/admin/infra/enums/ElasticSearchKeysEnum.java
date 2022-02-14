package com.search.admin.infra.enums;

/**
 * ElasticSearch 相关设置key
 */
public enum ElasticSearchKeysEnum implements KeyValueEnum<String>{
    SETTING_NUMBER_OF_SHARDS("index.number_of_shards","分片数"),
    SETTING_NUMBER_OF_REPLICAS("index.number_of_replicas","副本数");

    private String code;
    private String desc;

    ElasticSearchKeysEnum(String code, String desc) {
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
