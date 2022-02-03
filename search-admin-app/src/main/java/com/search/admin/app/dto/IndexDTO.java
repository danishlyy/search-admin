package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IndexDTO {

    private String indexId;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 索引描述
     */
    private String indexDesc;
}
