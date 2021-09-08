package com.ljf.redisson.controller;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ljf
 * @Create: 2021/9/8 13:32
 * @Description:
 **/
public class LockController {

    @Autowired
    private RedissonClient redissonClient;



}
