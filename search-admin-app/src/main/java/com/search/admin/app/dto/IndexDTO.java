package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class IndexDTO {

    private String indexId;

    /**
     * 索引名称
     */
    private String indexName;

    private String indexStatus;

    private String syncStatus;

    /**
     * 索引描述
     */
    private String indexDesc;

    private String numberOfShards;

    private String numberOfReplicas;

    private List<IndexMappingPropertiesDTO> fields;
}
