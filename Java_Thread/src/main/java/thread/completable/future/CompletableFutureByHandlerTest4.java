package thread.completable.future;

import java.util.concurrent.*;

/**
 * @Author: ljf
 * @Create: 2021/9/10 17:34
 * @Description:
 **/
public class CompletableFutureByHandlerTest4 {

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
            System.out.println("子线程:"+Thread.currentThread().getName()+"开始执行");
            int ii = 10/2;
            System.out.println("子线程:"+Thread.currentThread().getName()+"执行结果ii="+ii);
            return ii;
        }, executor).handle((result,exception)->{
            if (result != null){
                return 00;
            }
            else if (exception != null){
                return 11;
            }
            return -1;
        });
        System.out.println("结果:"+integerCompletableFuture.get());
    }
}
