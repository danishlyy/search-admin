package com.search.admin.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DictionaryBO {

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
