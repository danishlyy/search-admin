package com.search.admin.adapter.response;

import com.search.admin.adapter.base.DictionaryBaseVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class DictionaryVO extends DictionaryBaseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -6968315605427133404L;

    /**
     * 字典code
     */
    private String dictCode;

    /**
     * 字典描述
     */
    private String dictDesc;
}
