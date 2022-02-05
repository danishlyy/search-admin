package com.search.admin.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IndexMappingPropertiesBO {

    private String fieldName;
    private String fieldType;
}
