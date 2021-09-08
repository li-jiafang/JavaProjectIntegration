package com.ljf.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ljf
 * @Create: 2021/9/8 13:18
 * @Description:
 **/

@Configuration
public class RedissonConfig {


    /**
     * 所有对Redisson的使用都是通过RedissonClient对象来建立连接的
     * @return
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        // 默认连接地址 127.0.0.1:6379
        // 创建配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        // 根据配置创建RedissonClient实例
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }


}
