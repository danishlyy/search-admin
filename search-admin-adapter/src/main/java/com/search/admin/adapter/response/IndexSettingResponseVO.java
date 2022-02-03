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
public class IndexSettingResponseVO extends IndexBaseResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -670506471670872155L;

    /**
     * 索引的名称
     */
    private String indexName;

    /**
     * 索引分片数
     */
    private String numberOfShards;

    /**
     * 索引分片副本数
     */
    private String numberOfReplicas;


}
