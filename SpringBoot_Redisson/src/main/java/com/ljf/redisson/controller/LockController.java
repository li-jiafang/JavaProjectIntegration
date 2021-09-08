package com.ljf.redisson.controller;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
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
     * <p>
     * 不可重入锁:导致死锁
     * A加了锁1,B也要加锁1
     * A方法执行后,内部执行完B方法,才能解锁,
     * 但是B方法执行也需要加锁1,最终导致死锁
     *
     * @return
     */
    @GetMapping("/reentrantLock")
    public String testReentrantLock() {

        // 获取一把锁,只要锁的名字相同,则是同一把锁
        RLock lock = redisson.getLock("My-Lock");
        // 加锁
        lock.lock(); //阻塞式等待 默认加的锁时30s时间
        // 1 锁的自动续期,如果业务执行时间过长,锁的时间会自动续期30s,不用担心业务执行时间过长,导致锁过期
        // 2 加锁的业务只要运行完成,就不会给当前锁续期
        // 如果未指定锁的超时时间,则默认使用30s,而且只要占锁成功,会自动启动一个定时任务来给锁超时时间来续期(1/3时间后就再次续期)
        try {
            System.out.println("加锁成功,执行业务代码....." + Thread.currentThread().getName());
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("解锁成功....." + Thread.currentThread().getName());
            // 解锁,不管业务代码是否抛出异常,都需要解锁
            lock.unlock();
        }
        return "hello";
    }


    /**
     * 可重入锁
     * 指定锁的过期时间
     * <p>
     * 指定锁的过期时间后,锁将不再自动续期
     * 为什么会出现这种情况:
     * 如果指定的锁的超时时间,则默认使用设置的时间来执行脚本占锁
     * <p>
     * 推荐设置过期时间,避免了锁续期操作,而且只要设置过期时间合理,能避免锁的重复占用
     *
     * @return
     */
    @GetMapping("/reentrantLock2")
    public String testReentrantLock2() {

        // 获取一把锁,只要锁的名字相同,则是同一把锁
        RLock lock = redisson.getLock("My-Lock");
        // 加锁
        //阻塞式等待 指定锁的有效时间是10s
        lock.lock(10, TimeUnit.SECONDS);
        // 如果指定的锁的超时时间,则默认使用设置的时间来执行脚本占锁
        try {
            System.out.println("加锁成功,执行业务代码....." + Thread.currentThread().getName());
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("解锁成功....." + Thread.currentThread().getName());
            // 解锁,不管业务代码是否抛出异常,都需要解锁
            lock.unlock();
        }
        return "hello";
    }


    /**
     * 读写锁成对出现,保证读取最新的数据
     * 写锁是一个拍它锁,只要写锁存在,读和写都要进入等待状态
     *
     * 读锁是一个共享锁,读数据互想不影响(相当于不加锁),但是在读锁状态下,进行写操作,则写操作是阻塞状态,除非读操作结束
     * 写+读 写锁状态下,读进入阻塞状态
     *
     * 写+写 写进入阻塞状态
     *
     * 读+写 读锁状态下,写进入阻塞状态
     *
     * 读+读 数据共享,相当于没有锁
     *
     *
     */

    /**
     * 写锁
     *
     * @return
     */
    @GetMapping("/writeLock")
    public String testWriteLock() {

        // 获取读锁,锁的名字相同,就是同一把锁
        RReadWriteLock lock = redisson.getReadWriteLock("rw-Lock");
        RLock wLock = lock.writeLock();
        // 设置写锁
        wLock.lock();
        String s = "";
        try {
            s = UUID.randomUUID().toString();
            RBucket<Object> writeValue = redisson.getBucket("writeValue");
            writeValue.set(s);
            System.out.println("加写锁成功,执行业务代码....." + Thread.currentThread().getName());
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("解锁成功....." + Thread.currentThread().getName());
            // 解锁,不管业务代码是否抛出异常,都需要解锁
            wLock.unlock();
        }
        return "hello";
    }


    /**
     * 读锁
     *
     * @return
     */
    @GetMapping("/readLock")
    public String testReadLock() {

        // 获取一把锁,只要锁的名字相同,则是同一把锁
        RReadWriteLock lock = redisson.getReadWriteLock("rw-Lock");
        String name = "";
        RLock rLock = lock.readLock();
        // 设置读锁
        rLock.lock();
        // 如果指定的锁的超时时间,则默认使用设置的时间来执行脚本占锁
        try {
            RBucket<String> writeValue = redisson.getBucket("writeValue");
            name = writeValue.get();
            System.out.println("加读锁成功,执行业务代码....." + Thread.currentThread().getName());
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("解锁成功....." + Thread.currentThread().getName());
            // 解锁,不管业务代码是否抛出异常,都需要解锁
            rLock.unlock();
        }
        return name;
    }


    /**
     * 信号量(Semaphore)
     *
     * 车库停车 假设三个车位,来一辆车占用一个车位,走一辆车释放一个车位
     *
     * 功能演示执行:
     * parkingSpace 停车位
     * 缓存中设定parkingSpace=3,三个停车位
     * 直接执行park(停车),占了一个车位 ,parkingSpace-1 = 2
     * 再次执行等于1
     * 再次执行等于0
     * 如果还要再次执行,则进入阻塞状态
     *
     * 此时执行go(将车开走),则阻塞状态下的线程可执行
     *
     * 再次执行parkingSpace+1
     *
     *
     * 信号量 :可用于分布式限流
     *
     */
    /**
     * 停车
     * @return
     */
    @GetMapping("/park")
    public String park() throws InterruptedException {

        RSemaphore park = redisson.getSemaphore("parkingSpace");
        boolean b = park.tryAcquire();
        if (b){
            // return 执行业务
        }else{
            // return 流量过大,限流,不可使用
        }
        //park.acquire(); // 获取一个信号,获取一个值,占一个车位

        return "OK";
    }


    /**
     * 将车开走
     * @return
     */
    @GetMapping("/go")
    public String go() {
        RSemaphore park = redisson.getSemaphore("parkingSpace");
        park.release(); // 释放一个信号,释放一个值,将车开走
        return "OK";
    }









}
