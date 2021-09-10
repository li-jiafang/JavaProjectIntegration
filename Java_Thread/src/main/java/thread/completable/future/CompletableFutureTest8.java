package thread.completable.future;

import java.util.concurrent.*;

/**
 * @Author: ljf
 * @Create: 2021/9/10 18:15
 * @Description:
 *
 * 异步编排之多任务组合
 **/
public class CompletableFutureTest8 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                200,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程:" + Thread.currentThread().getName() + "开始执行");
            int ii = 10 / 2;
            System.out.println("子线程:" + Thread.currentThread().getName() + "执行结果ii=" + ii);
            System.out.println("任务一执行结束");
            return ii;
        }, executor);

        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程:" + Thread.currentThread().getName() + "开始执行");
            int ii = 10 / 2;
            System.out.println("子线程:" + Thread.currentThread().getName() + "执行结果ii=" + ii);
            System.out.println("任务二执行结束");
            return ii+"hello";
        }, executor);

        // 方式一: runAfterBothAsync : 组合任务1和2,任务1 2执行完成,才执行任务3
        // 无法获取任务1和任务2的结果,没有返回值
        future01.runAfterBothAsync(future02,()->{
            System.out.println("任务三 执行开始");
            System.out.println("任务三 执行结束");
        },executor);

        // 方式二: thenAcceptBothAsync : 组合任务1和2,任务1 2执行完成,才执行任务3
        // 能获取任务1和任务2的结果,没有返回值
        future01.thenAcceptBothAsync(future02,(f1,f2)->{
            System.out.println("任务三 执行开始");
            System.out.println("输出任务1的结果"+f1);
            System.out.println("输出任务2的结果"+f2);
            System.out.println("任务三 执行结束");
        },executor);

        // 方式三: thenCombineAsync : 组合任务1和2,任务1 2执行完成,才执行任务3
        // 无法获取任务1和任务2的结果,有返回值
        CompletableFuture<String> stringCompletableFuture = future01.thenCombineAsync(future02, (f1, f2) -> {
            System.out.println("任务三 执行开始");
            System.out.println("任务三 执行结束");

            return f1 + f2 + "哈哈哈深刻的合法";
        }, executor);
        System.out.println("结果:"+stringCompletableFuture.get());
    }
}
