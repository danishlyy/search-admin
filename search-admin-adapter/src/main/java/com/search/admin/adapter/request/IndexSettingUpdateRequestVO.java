package com.search.admin.adapter.request;

import com.search.admin.adapter.base.IndexBaseRequestVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndexSettingUpdateRequestVO extends IndexBaseRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7406465604046827145L;

    @NotBlank(message = "索引副本数不可以为空，至少为1")
    private String numberOfReplicas;

    @NotBlank(message = "索引描述不可以为空")
    private String indexDesc;

}
