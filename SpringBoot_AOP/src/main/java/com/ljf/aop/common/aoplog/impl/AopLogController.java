package com.ljf.aop.common.aoplog.impl;

import com.ljf.aop.common.aoplog.AopLogAbstract;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AopLogController extends AopLogAbstract{

    @Pointcut("execution(public * com.ljf.aop.controller.*Controller.*(..))")
    @Override
    public void log() {

    }


    @Before("log()")
    @Override
    public void before(JoinPoint joinPoint) {
        System.out.println("前置通知开始");
        doExecute(joinPoint);
    }


    @After("log()")
    @Override
    public void after(JoinPoint joinPoint) {
        doExecute(joinPoint);
    }


    @AfterReturning(pointcut = "log()",returning = "returnValue")
    @Override
    public Object afterReturn(JoinPoint joinPoint, Object returnValue) {
        return null;
    }


    @AfterThrowing(pointcut = "log()",throwing = "e")
    @Override
    public void afterThrowing(JoinPoint joinPoint, Throwable e){
        doExecute(joinPoint);
    }


//    @Around("log()")
//    public String around(ProceedingJoinPoint joinPoint){
//        System.out.println("环绕通知开始");
//        try {
//            System.out.println("Taking seats");
//            System.out.println("Silencing cell phones");
//
//            joinPoint.proceed();
//
//            System.out.println("CLAP CLAP CLAP!!!");
//        } catch (Throwable throwable) {
//            System.out.println("Demanding a refund");
//        } finally {
//            System.out.println("perform finish");
//        }
//        return "hello";
//    }

}
