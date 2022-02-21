package com.search.admin.adapter.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class ReIndexRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 672916496041722966L;

    @NotBlank(message = "源索引名称不可为空")
    private List<String> sourceIndexName;

    @NotBlank(message = "目标索引名称不可为空")
    private String targetIndexName;
}
