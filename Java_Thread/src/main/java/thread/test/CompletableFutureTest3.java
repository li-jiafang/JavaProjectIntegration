package thread.test;

import java.util.concurrent.*;

/**
 * @Author: ljf
 * @Create: 2021/9/10 17:19
 * @Description:
 * 异步编排CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync() 执行
 * 输出正常和错误信息:   whenComplete()
 * 如果代码异常返回错误信息: 如果代码有异常才执行exceptionally方法
 *
 **/
public class CompletableFutureTest3 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                200,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("开始执行");
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程:"+Thread.currentThread().getName()+"开始执行");
            int ii = 10/0;
            System.out.println("子线程:"+Thread.currentThread().getName()+"执行结果ii="+ii);
            return ii;
        }, executor).whenComplete((result,exception)->{
            System.out.println("whenComplete正常结果result="+result);
            System.out.println("whenComplete异常信息exception="+exception);
        }).exceptionally((exception)->{
            if (exception.getCause() instanceof ArithmeticException){
                System.out.println("这是计算失误的异常");
            }
            System.out.println("exceptionally exception"+exception);
            return 10;
        });


    }
}
