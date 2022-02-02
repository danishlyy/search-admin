package com.search.admin.infra.ex;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFrameworkException extends RuntimeException{

    private String errorCode;
    private String errMsg;


    public SearchFrameworkException(String errorCode,String errMsg){
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

}
