package com.search.admin.adapter.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class IndexBaseResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7289182689420238094L;

    private String indexId;
}
