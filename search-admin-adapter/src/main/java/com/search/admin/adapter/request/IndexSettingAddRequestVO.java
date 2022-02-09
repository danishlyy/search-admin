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
public class IndexSettingAddRequestVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1779844094455672773L;

    /**
     * 索引名称
     */
    @IndexNameLimitation
    @NotBlank(message = "索引名称不可以为空")
    private String indexName;

    /**
     * 索引描述
     */
    @NotBlank(message = "索引描述不可以为空")
    private String indexDesc;

    /**
     * 分片数
     */
    @NotBlank(message = "分片数不可以为空，必须大于等于1")
    private String numberOfShards;
    /**
     * 副本数
     */
    @NotBlank(message = "副本数不可以为空，必须大于等于1")
    private String numberOfReplicas;
}
