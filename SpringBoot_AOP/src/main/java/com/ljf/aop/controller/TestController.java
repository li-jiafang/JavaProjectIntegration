package com.ljf.aop.controller;

import com.ljf.aop.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: ljf
 * @Create: 2021/9/20 17:23
 * @Description:
 **/
@RestController
@RequestMapping("/hello")
public class TestController {

    @Resource
    private TestService testService;


    @GetMapping("/test")
    public String test(String name){

        String str = testService.getStr(name);
        return str+" world";

    }


}
