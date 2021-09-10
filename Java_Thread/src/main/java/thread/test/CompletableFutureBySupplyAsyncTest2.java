package thread.test;

import java.util.concurrent.*;

/**
 * @Author: ljf
 * @Create: 2021/9/10 17:12
 * @Description:
 *  CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync() 执行
 *
 **/
public class CompletableFutureBySupplyAsyncTest2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                200,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("开始执行");
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            int ii = 0;
            for (int i = 0; i < 100; i++) {
                ii = i;
                System.out.println("子线程:" + Thread.currentThread().getName() + i + "开始执行");
            }
            return ii;
        }, executor);

        System.out.println("子线程执行结果ii="+integerCompletableFuture.get());
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程:"+Thread.currentThread().getName()+i+"开始执行");
        }
    }
}
