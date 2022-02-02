package com.search.admin.adapter.request;

import com.search.admin.infra.validate.IndexNameLimitation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndexSettingRequestVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1779844094455672773L;
    /**
     * 索引id
     */
    private String indexId;
    /**
     * 索引名称
     */
    @IndexNameLimitation
    @NotBlank
    private String indexName;

    /**
     * 分片数
     */
    @NotBlank(message = "numberOfShards cannot be null,at least 1")
    private String numberOfShards;
    /**
     * 副本数
     */
    @NotBlank(message = "numberOfReplicas cannot be null,at least 1")
    private String numberOfReplicas;
}
