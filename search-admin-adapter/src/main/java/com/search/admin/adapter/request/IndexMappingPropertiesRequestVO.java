package com.search.admin.adapter.request;

import com.search.admin.infra.validate.FieldTypeValid;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndexMappingPropertiesRequestVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4357312924567105002L;

    @NotBlank(message = "索引fieldName不可以为空")
    private String fieldName;
    @NotBlank(message = "索引fieldType不可以为空")
    @FieldTypeValid
    private String fieldType;
}
