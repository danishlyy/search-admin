package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class AnalyzerDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1903532278344506065L;
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
