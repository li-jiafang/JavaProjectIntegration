package com.ljf.redisson.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: ljf
 * @Create: 2021/9/8 13:32
 * @Description:
 **/
@RestController
public class LockController {

    @Autowired
    private RedissonClient redisson;


    /**
     * 可重入锁
     * 解释
     * A方法中有B方法
     * A方法需要增加锁1,B方法也需要增加一把锁1
     * A方法加锁1执行到B方法,发现B方法也要加锁1,则B方法直接加锁1,称之为可重入锁
     *
     * 不可重入锁:导致死锁
     * A加了锁1,B也要加锁1
     * A方法执行后,内部执行完B方法,才能解锁,
     * 但是B方法执行也需要加锁1,最终导致死锁
     *
     *
     * @return
     */
    @GetMapping("/reentrantLock")
    public String testReentrantLock(){

        // 获取一把锁,只要锁的名字相同,则是同一把锁
        RLock lock = redisson.getLock("My-Lock");
        // 加锁
        lock.lock(); //阻塞式等待 默认加的锁时30s时间
        // 1 锁的自动续期,如果业务执行时间过长,锁的时间会自动续期30s,不用担心业务执行时间过长,导致锁过期
        // 2 加锁的业务只要运行完成,就不会给当前锁续期
        try {
            System.out.println("加锁成功,执行业务代码....."+Thread.currentThread().getName());
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("解锁成功....."+Thread.currentThread().getName());
            // 解锁,不管业务代码是否抛出异常,都需要解锁
            lock.unlock();
        }
        return "hello";
    }


    /**
     * 可重入锁
     * 指定锁的过期时间
     *
     * @return
     */
    @GetMapping("/reentrantLock2")
    public String testReentrantLock2(){

        // 获取一把锁,只要锁的名字相同,则是同一把锁
        RLock lock = redisson.getLock("My-Lock");
        // 加锁
        //阻塞式等待 指定锁的有效时间是10s
        lock.lock(10, TimeUnit.SECONDS);
        // 1 锁的自动续期,如果业务执行时间过长,锁的时间会自动续期30s,不用担心业务执行时间过长,导致锁过期
        // 2 加锁的业务只要运行完成,就不会给当前锁续期
        try {
            System.out.println("加锁成功,执行业务代码....."+Thread.currentThread().getName());
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("解锁成功....."+Thread.currentThread().getName());
            // 解锁,不管业务代码是否抛出异常,都需要解锁
            lock.unlock();
        }
        return "hello";
    }



}
