package com.ljf.aop.common.aoplog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: ljf
 * @Create: 2021/9/20 17:26
 * @Description:
 **/

public interface AopLog {

    /**
     * 切入点
     */
    void log();

    /**
     * 前置通知 目标方法执行之前通知
     * @param joinPoint
     */
    void before(JoinPoint joinPoint);

    /**
     * 后置通知 目标方法之后执行(始终执行)
     * @param joinPoint
     */
    void after(JoinPoint joinPoint);

    /**
     * 返回后通知 执行方法结束前执行(异常不执行)
     * @param joinPoint
     * @param returnValue
     * @return
     */
    Object afterReturn(JoinPoint joinPoint, Object returnValue);

    /**
     * 异常通知 出现异常后执行
     * @param joinPoint
     * @param e 抛出的异常
     */
    void afterThrowing(JoinPoint joinPoint, Throwable e);

    /**
     *环绕通知 环绕目标方法执行
     * @param joinPoint
     * @throws Throwable
     */
//    void around(ProceedingJoinPoint joinPoint);


}
