package com.search.admin.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class IndexBO {

    private String indexName;
    private List<IndexMappingPropertiesBO> fields;
}