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
public class DictionaryUpdateRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6994111816518446367L;

    /**
     * 字典记录主键id
     */
    @NotBlank(message = "字典id不可以为空")
    private String id;
    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不可为空")
    private String dictType;

    /**
     * 字典代码
     */
    @NotBlank(message = "字典代码不可为空")
    private String dictCode;

    /**
     * 字典值
     */
    @NotBlank(message = "字典值不可为空")
    private String dictValue;

    /**
     * 字典描述
     */
    @NotBlank(message = "字典描述信息不可为空")
    private String dictDesc;
}
