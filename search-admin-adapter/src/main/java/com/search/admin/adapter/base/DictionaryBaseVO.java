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
public class DictionaryBaseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3803670239595741735L;
    @NotBlank(message = "字典id不可为空")
    private String id;
}
