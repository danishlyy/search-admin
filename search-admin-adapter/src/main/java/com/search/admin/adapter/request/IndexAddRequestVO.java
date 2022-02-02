package com.search.admin.adapter.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@ToString
public class IndexAddRequestVO implements Serializable {

    @NotBlank(message = "索引名称indexName不可以为空")
    private String indexName;

    @NotEmpty
    @Valid
    private List<IndexMappingPropertiesRequestVO> fields;
}
