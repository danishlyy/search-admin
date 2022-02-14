package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class IndexMappingPropertiesDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3501572012942938003L;

    private String fieldName;
    private String fieldType;
    private String analyzeFlag;
    private String analyzeType;
}
