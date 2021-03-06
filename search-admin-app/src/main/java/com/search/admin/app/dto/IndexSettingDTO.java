package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndexSettingDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8176629575046183147L;

    /**
     * 索引id
     */
    private String indexId;
    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 索引描述
     */
    private String indexDesc;

    /**
     * 同步状态
     */
    private String syncStatus;

    /**
     * 分片数
     */
    private String numberOfShards;
    /**
     * 副本数
     */
    private String numberOfReplicas;
}
