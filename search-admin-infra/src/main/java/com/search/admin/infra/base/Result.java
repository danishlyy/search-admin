package com.search.admin.infra.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Result<T> implements Serializable {

    public static final String SUCCESS_CODE = "00000000";
    public static final String SUCCESS_Message = "response success";

    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        result.setMsg(SUCCESS_Message);
        return result;
    }

    public static <T> Result<T> failOfCode(String code,String msg){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
