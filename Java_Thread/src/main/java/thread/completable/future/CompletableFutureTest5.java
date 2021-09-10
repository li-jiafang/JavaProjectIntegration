package thread.completable.future;

import java.util.concurrent.*;

/**
 * @Author: ljf
 * @Create: 2021/9/10 17:53
 * @Description: 线程串行化之thenRunAsync():串行化执行,不能感知上一步返回结果,也没有返回值
 **/
public class CompletableFutureTest5 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                    200,
                    10,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(100000),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());
            System.out.println("开始执行");
            CompletableFuture.supplyAsync(() -> {
                System.out.println("子线程:" + Thread.currentThread().getName() + "开始执行");
                int ii = 10 / 2;
                System.out.println("子线程:" + Thread.currentThread().getName() + "执行结果ii=" + ii);
                System.out.println("任务一开始执行");
                return ii;
            }, executor).thenRunAsync(()->{
                System.out.println("任务2开始执行了");
            },executor);
    }

}
