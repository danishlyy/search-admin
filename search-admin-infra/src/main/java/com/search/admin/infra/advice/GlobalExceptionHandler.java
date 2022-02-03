package com.search.admin.infra.advice;

import com.search.admin.infra.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String RESPONSE_FAIL_CODE = "99999999";
    public static final String RESPONSE_VALIDATE_FAIL_CODE = "99999998";
    public static final String RESPONSE_FAIL_MSG = "系统繁忙，请稍后再尝试";

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result methodParamValidateHandler(MethodArgumentNotValidException ex) {
        log.warn("method param validate failed", ex);
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        String errorMsg = allErrors.stream().map(ObjectError::getDefaultMessage).sorted().distinct().collect(Collectors.joining(";"));
        return Result.failOfCode(RESPONSE_VALIDATE_FAIL_CODE, errorMsg);
    }

    @ExceptionHandler(value = Exception.class)
    public Result<String> errorHandler(Exception ex) {
        log.warn("exception stacktrace:", ex);
        return Result.failOfCode(RESPONSE_FAIL_CODE, RESPONSE_FAIL_MSG);
    }

}
