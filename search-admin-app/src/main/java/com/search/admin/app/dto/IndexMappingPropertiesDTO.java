package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IndexMappingPropertiesDTO {

    private String fieldName;
    private String fieldType;
    private String analyzeFlag;
    private String analyzeType;
}
