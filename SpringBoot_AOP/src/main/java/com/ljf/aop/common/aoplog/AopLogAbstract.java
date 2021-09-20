package com.ljf.aop.common.aoplog;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: ljf
 * @Create: 2021/9/20 18:36
 * @Description:
 **/
@Slf4j
public abstract class AopLogAbstract implements AopLog {

    private static final String UNKNOWN = "unknown";


    public void doExecute(JoinPoint point){

        System.out.println("joinPoint serviceLog");

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        // 获取ip地址
        String ip = getIp(request);
        log.info("ip:{}",ip);
        // 获取请求url
        String url = request.getRequestURL().toString();
        // 获取请求方法get  post  put delete
        String httpMethod = request.getMethod();

        String declaringTypeName = point.getSignature().getDeclaringTypeName();
        String name = point.getSignature().getName();
        // 获取类名和方法名
        String classMethod = String.format("%s.%s",declaringTypeName,name);
        // 获取方法参数名和参数值
        Map<String, Object> nameAndValue = getNameAndValue(point);
        String header = request.getHeader("User-Agent");


        System.out.println("");

    }



    /**
     *  获取方法参数名和参数值
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getNameAndValue(JoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            //log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }


    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                //log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

}
