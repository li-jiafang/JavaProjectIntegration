package com.ljf.redisson;

import org.junit.jupiter.api.Test;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootRedissonApplicationTests {

    @Autowired
    public RedissonClient redissonClient;

    @Test
    void contextLoads() {
        System.out.println(redissonClient);
    }

}
