package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class IndexAddDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3578458846973615249L;

    private String indexId;
    private String indexName;
    private List<IndexMappingPropertiesDTO> fields;
}
