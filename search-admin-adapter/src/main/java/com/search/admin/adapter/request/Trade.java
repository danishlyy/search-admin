package com.search.admin.adapter.request;

import lombok.Data;

@Data
public class Trade {

    private String orderNo;
    private String orderDate;

    private Customer customer;

}
