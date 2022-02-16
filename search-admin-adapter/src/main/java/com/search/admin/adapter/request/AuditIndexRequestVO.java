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
public class AuditIndexRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7507538722057632514L;

    @NotBlank(message = "审核唯一key不可以为空")
    private String auditId;


    @NotBlank(message = "同步类型不可为空")
    private String syncType;

    @NotBlank(message = "索引id不可为空")
    private String indexSettingsId;
}
