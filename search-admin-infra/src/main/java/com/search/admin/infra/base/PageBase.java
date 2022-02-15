package com.search.admin.infra.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class PageBase implements Serializable {

    @Serial
    private static final long serialVersionUID = -6601555875583204378L;

    private String pageNumber;
    private String pageSize;
}
