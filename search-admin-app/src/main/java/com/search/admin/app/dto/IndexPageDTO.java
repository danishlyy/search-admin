package com.search.admin.app.dto;

import com.search.admin.infra.base.PageBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndexPageDTO extends PageBase implements Serializable {
    @Serial
    private static final long serialVersionUID = -1020261326172024033L;

    private String indexName;

    private String indexStatus;

}
