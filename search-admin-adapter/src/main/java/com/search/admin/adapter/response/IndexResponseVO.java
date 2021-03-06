package com.search.admin.adapter.response;

import com.search.admin.adapter.base.IndexBaseResponseVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndexResponseVO extends IndexBaseResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5836358704732354913L;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 索引描述
     */
    private String indexDesc;
    /**
     * 索引状态描述
     */
    private String indexStatusDesc;
    /**
     * 索引状态代码
     */
    private String indexStatus;
    /**
     * 分片数
     */
    private String numberOfShards;
    /**
     * 副本数
     */
    private String numberOfReplicas;
}
