package com.search.admin.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnalyzerBO {

    /**
     * 分词器
     */
    private String analyzer;
    /**
     * 待分词文本
     */
    private String text;

    private String indexName;
    /**
     * {@link com.search.admin.infra.enums.AnalyzerTypeEnum}
     * 分词器分类
     */
    private String analyzerType;
}
