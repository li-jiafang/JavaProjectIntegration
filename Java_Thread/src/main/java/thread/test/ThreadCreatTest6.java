package thread.test;

import thread.creat.Runnable2;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ljf
 * @Create: 2021/9/10 12:02
 * @Description:
 **/
public class ThreadCreatTest6 {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                200,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        System.out.println("开始执行------");
        executor.execute(new Runnable2());

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程"+i);
        }
        System.out.println("结束");

    }
}
