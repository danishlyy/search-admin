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
public class DictionaryDeleteRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1912683648107601519L;

    @NotBlank(message = "字典id不可为空")
    private String id;
}
