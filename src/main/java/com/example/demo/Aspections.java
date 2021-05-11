package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class Aspections {
    @Pointcut("within(com.example.demo.Services.*)")
    public void allServiceMethods() {}
    @Around("allServiceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long baseTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long completeTime = System.currentTimeMillis() - baseTime;
        log.info("Method time: " + completeTime + "ms");
        return proceed;
    }
}