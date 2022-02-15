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
public abstract class IndexBaseRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1135258923807818692L;

    /**
     * 索引id
     */
    @NotBlank(message = "索引id不可以为空")
    private String indexId;
}
