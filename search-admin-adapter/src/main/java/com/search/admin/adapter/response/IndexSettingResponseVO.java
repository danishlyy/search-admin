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
     * 索引描述
     */
    private String indexDesc;

    /**
     * 同步状态 为1 表示已同步 不可修改分片数
     * {@link com.search.admin.infra.enums.SyncStatusEnum}
     */
    private String syncStatus;

    /**
     * 索引分片数
     */
    private String numberOfShards;

    /**
     * 索引分片副本数
     */
    private String numberOfReplicas;


}
