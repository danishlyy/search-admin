package com.search.admin.adapter.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class PageRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8479073253540444891L;

    /**
     * 查询第几页
     */
    @NotBlank(message = "分页参数必传")
    private String pageNumber = "1";

    /**
     * 每页显示的个数
     */
    @NotBlank(message = "每页查询的条数必传")
    private String pageSize = "10";
}
