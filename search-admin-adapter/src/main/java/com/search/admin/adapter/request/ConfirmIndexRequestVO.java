package com.search.admin.adapter.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@ToString
public class ConfirmIndexRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7507538722057632514L;

    @NotBlank(message = "索引id不可以为空")
    private String indexId;
}
