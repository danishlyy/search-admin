package com.search.admin.adapter.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndexMappingPropertiesResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3989724667095684414L;
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 字段类型
     */
    private String fieldType;
    /**
     * 是否分词
     */
    private String analyzeFlag;

    /**
     * 分词器类型
     */
    private String analyzeType;
}
