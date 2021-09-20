package com.ljf.aop.common.aoplog.impl;

import com.ljf.aop.common.aoplog.AopLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: ljf
 * @Create: 2021/9/20 18:14
 * @Description:
 **/
@Aspect
@Component
public class AopLogService implements AopLog {

    @Override
    @Pointcut("execution(public * com.ljf.aop.service.*Service.*(..))")
    public void log() {

    }

    @Override
    @Before("log()")
    public void before(JoinPoint joinPoint) {
        System.out.println("开始执行Service层 前置通知");
    }

    @Override
    @After("log()")
    public void after(JoinPoint joinPoint) {

    }

    @Override
    @AfterReturning(pointcut = "log()",returning = "returnValue")
    public Object afterReturn(JoinPoint joinPoint, Object returnValue) {
        return null;
    }

    @Override
    @AfterThrowing(pointcut = "log()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e){

    }

//    @Override
//    @Around("log()")
//    public void around(ProceedingJoinPoint joinPoint){
//
//    }
}
