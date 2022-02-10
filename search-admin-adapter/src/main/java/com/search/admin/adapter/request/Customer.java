package com.search.admin.adapter.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 3293246961553312067L;

    private String userName;
    private String phone;
}
