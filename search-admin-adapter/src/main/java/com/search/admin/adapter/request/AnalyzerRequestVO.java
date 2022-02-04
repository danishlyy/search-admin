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
public class AnalyzerRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2266720848826698056L;
    /**
     * 分词器
     */
    @NotBlank(message = "分词器不可以为空")
    private String analyzer;
    /**
     * 待分词文本
     */
    @NotBlank(message = "需要分词的文本不可以为空")
    private String text;
}
