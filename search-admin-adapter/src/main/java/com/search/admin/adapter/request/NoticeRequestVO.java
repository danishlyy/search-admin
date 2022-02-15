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
public class NoticeRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3962226320503917834L;

    @NotBlank(message = "索引id不可以为空")
    private String indexId;

    @NotBlank(message = "索引名称不可以为空")
    private String indexName;

    @NotBlank(message = "索引的状态不可以为空")
    private String indexStatus;

    @NotBlank(message = "同步类型必填")
    private String syncType;

    @NotBlank(message = "是否需要reIndex必填")
    private String reindexFlag;


}
