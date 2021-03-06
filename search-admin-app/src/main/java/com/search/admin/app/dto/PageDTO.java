package com.search.admin.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class PageDTO<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4319719565972028471L;


    private String pages;

    private String total;

    private String current;

    private List<T> records;

}
