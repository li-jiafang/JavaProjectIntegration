package thread.completable.future;

import java.util.concurrent.*;

/**
 * @Author: ljf
 * @Create: 2021/9/10 17:01
 * @Description:
 *
 * 异步编排
 * CompletableFuture.runAsync(Runnable,Executor) 方法执行,没有返回结果
 **/
public class CompletableFutureByRunAsyncTest1 {


    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                200,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("开始执行");
       CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("子线程:" + Thread.currentThread().getName() + i + "开始执行");
            }
        }, executor);

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程:"+Thread.currentThread().getName()+i+"开始执行");
        }
    }
}
