package com.search.admin.infra.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class RequestLogAspect {

    @Pointcut("execution(public * com.search.admin..*.*(..))")
    public void execute() {

    }

    @Before("execute()")
    public void executeBefore(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        ServletRequestAttributes req = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = req.getRequest();
        log.info(MessageFormat.format("url:{0},http method:{1},class:{2}, method:{3},ip:{4},args:{5}",
                request.getRequestURL() != null ? request.getRequestURL().toString() : "requestUrl is null",
                request.getMethod(),
                joinPoint.getSignature() != null ? joinPoint.getSignature().getDeclaringTypeName() : "declaringTypeName is null",
                joinPoint.getSignature() != null ? joinPoint.getSignature().getName() : "method name is null",
                request.getRemoteAddr(),
                joinPoint.getArgs() != null ? Arrays.asList(joinPoint.getArgs()) : "no args"));

    }

//    @Around("execute()")
//    public Result executeAround(ProceedingJoinPoint joinPoint) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start(joinPoint.getSignature().getName());
//        Result proceed = null;
//        try {
//            proceed = (Result) joinPoint.proceed();
//        } catch (Throwable e) {
//            log.error("execute around failed", e);
//            SearchFrameworkException searchFrameworkException = (SearchFrameworkException) e;
//            return Result.failOfCode(searchFrameworkException.getErrorCode(),searchFrameworkException.getErrMsg());
//        }
//        stopWatch.stop();
//        log.info("consume time:{}", stopWatch.prettyPrint());
//        return proceed;
//    }


}
