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
public class DictionaryAddRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7273425900682192005L;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不可为空")
    private String dictType;

    /**
     * 字典代码
     */
    @NotBlank(message = "字典代码不可以为空")
    private String dictCode;

    /**
     * 字典值
     */
    @NotBlank(message = "字典值不可以为空")
    private String dictValue;

    /**
     * 字典描述
     */
    @NotBlank(message = "字典描述不可以为空")
    private String dictDesc;
}
