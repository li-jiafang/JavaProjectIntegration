package com.ljf.sourcecode.test;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ljf
 * @Create: 2021/9/13 22:19
 * @Description:
 **/

@Configuration
//@ConditionalOnProperty(name = "enable.autoConfiguration",matchIfMissing = true)
@ConditionalOnProperty(name = "EnableAutoConfiguration",matchIfMissing = true)
public class MyStarter {

    static {
        System.out.println("==================MyStarter Init===================");
    }
}
