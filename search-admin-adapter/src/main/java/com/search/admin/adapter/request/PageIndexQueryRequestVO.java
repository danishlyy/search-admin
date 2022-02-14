package com.search.admin.adapter.request;

import com.search.admin.adapter.base.PageRequestVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class PageIndexQueryRequestVO extends PageRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -8659705099774180258L;

    private String indexName;

    private String indexStatus;


}
