package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class DictionaryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3951941001050500376L;

    private String id;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 字典值
     */
    private String dictValue;

    private String dictDesc;

    private String deleteFlag;
}
