package com.search.admin.domain.bo;

import com.search.admin.infra.base.PageBase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IndexPageConditionBO extends PageBase {

    private String indexName;

    private String indexStatus;

}
