package thread.completable.future;

import java.util.concurrent.*;

/**
 * @Author: ljf
 * @Create: 2021/9/10 18:03
 * @Description:
 * 线程串行化之thenApply():串行化执行,能感知上一步返回结果并输出,也能输出返回值
 *
 **/
public class CompletableFutureTest7 {

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
        }, executor).thenApply((result)->{
            System.out.println("任务2开始执行,获取任务1返回结果:"+result);
            return "感知到上一个任务执行结果,并返回="+result;

        });

    }
}
