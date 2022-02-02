package com.search.admin.app.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class IndexAddDTO {
    private String indexName;
    private List<IndexMappingPropertiesDTO> fields;
}
