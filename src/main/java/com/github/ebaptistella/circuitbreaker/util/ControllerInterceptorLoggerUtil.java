package com.github.ebaptistella.circuitbreaker.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ControllerInterceptorLoggerUtil {

    private Logger log = LoggerFactory.getLogger(ControllerInterceptorLoggerUtil.class);

    @Before("execution(public * com.github.ebaptistella.circuitbreaker..*Controller.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) {
        log.trace("==>ControllerInterceptorLogger: Executando o método {}", pjp);
    }

    @After("execution(public * com.github.ebaptistella.circuitbreaker..*Controller.*(..))")
    public void logAfterRestCall(JoinPoint pjp) {
        log.trace("==>ControllerInterceptorLogger: Saindo do método: {}", pjp);
    }

}
