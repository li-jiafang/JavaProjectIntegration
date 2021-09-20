package com.ljf.aop.service.impl;

import com.ljf.aop.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @Author: ljf
 * @Create: 2021/9/20 17:24
 * @Description:
 **/

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String getStr(String name) {


        return "hello "+name;
    }
}
