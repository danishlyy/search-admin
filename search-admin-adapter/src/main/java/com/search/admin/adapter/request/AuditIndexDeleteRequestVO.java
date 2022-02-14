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
public class AuditIndexDeleteRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7373531020997332022L;

    @NotBlank(message = "索引名称不可以为空")
    private String indexName;

    @NotBlank(message = "审核记录唯一条件id不可为空")
    private String id;
}
