package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DictionaryDTO {

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
