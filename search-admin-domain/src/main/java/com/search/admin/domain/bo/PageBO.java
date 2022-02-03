package com.search.admin.domain.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PageBO<T> {


    private String pages;

    private String total;

    private String current;

    private List<T> records;

}
