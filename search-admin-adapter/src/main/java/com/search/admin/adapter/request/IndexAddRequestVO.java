package com.search.admin.adapter.request;

import com.search.admin.adapter.base.IndexBaseRequestVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@ToString
public class IndexAddRequestVO extends IndexBaseRequestVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5713968543145957084L;

    @NotBlank(message = "索引名称indexName不可以为空")
    private String indexName;

    @NotEmpty
    @Valid
    private List<IndexMappingPropertiesRequestVO> fields;
}
