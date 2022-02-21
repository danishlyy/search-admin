package com.search.admin.adapter.response;

import com.search.admin.adapter.base.IndexBaseResponseVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class IndexMappingResponseVO extends IndexBaseResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -443918418432526906L;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 索引配置同步状态，如果是已同步，不可修改字段名称、字段类型
     */
    private String syncStatus;

    /**
     * 索引field列表
     */
    private List<IndexMappingPropertiesResponseVO> fields;


}
