package com.search.admin.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IndexPageConditionBO {

    private String indexName;

    private String indexStatus;

    private String pageNumber;

    private String pageSize;

}
