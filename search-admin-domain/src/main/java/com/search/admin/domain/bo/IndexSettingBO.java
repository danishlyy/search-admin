package com.search.admin.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IndexSettingBO {

    /**
     * 索引id
     */
    private String indexId;
    /**
     * 索引名称
     */
    private String indexName;

    private String indexDesc;

    /**
     * 分片数
     */
    private String numberOfShards;
    /**
     * 副本数
     */
    private String numberOfReplicas;


    private String indexMapping;


}
