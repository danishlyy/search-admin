package com.search.admin.adapter.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class CustomAnalyzerRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 570712376598888057L;

    /**
     * 分词器
     */
    @NotBlank(message = "分词器不可以为空")
    private String analyzer;

    /**
     * 索引名称
     */
    @NotBlank(message = "索引名称必须有")
    private String indexName;
    /**
     * 待分词文本
     */
    @NotBlank(message = "需要分词的文本不可以为空")
    private String text;


}
