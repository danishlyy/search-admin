package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnalyzerDTO {

    /**
     * 分词器
     */
    private String analyzer;
    /**
     * 待分词文本
     */
    private String text;

    private String indexName;

    private String analyzerType;
}
