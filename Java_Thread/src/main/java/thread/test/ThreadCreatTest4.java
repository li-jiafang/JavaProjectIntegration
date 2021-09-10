package thread.test;

import thread.creat.Callable4;
import thread.creat.Runnable2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: ljf
 * @Create: 2021/9/10 11:11
 * @Description:
 *
 * 使用Callable+FutureTask来创建线程
 **/
public class ThreadCreatTest4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("开始执行方法:");

        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable4());
        new Thread(integerFutureTask).start();

        Integer integer = integerFutureTask.get();

        System.out.println("子线程结束返回结果ii="+integer);


        for (int i = 0; i < 100; i++) {
            System.out.println("主线程:"+Thread.currentThread().getName()+i+"开始执行");
        }

    }
}
