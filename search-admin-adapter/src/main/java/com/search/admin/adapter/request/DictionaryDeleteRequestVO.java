package com.search.admin.adapter.request;

import com.search.admin.adapter.base.DictionaryBaseVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class DictionaryDeleteRequestVO extends DictionaryBaseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1912683648107601519L;

}
