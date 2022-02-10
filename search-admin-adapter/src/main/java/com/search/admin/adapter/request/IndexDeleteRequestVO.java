package com.search.admin.adapter.request;

import com.search.admin.adapter.base.IndexBaseRequestVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndexDeleteRequestVO extends IndexBaseRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1003925435676599258L;
}
