package com.search.admin.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class IndexBO {

    private String indexId;
    private String indexName;
    private String indexMapping;
    private String syncStatus;
    private List<IndexMappingPropertiesBO> fields;
}
