package thread.test;

import thread.creat.ThreadCreat;
import thread.creat.ThreadRunnable;

/**
 * @Author: ljf
 * @Create: 2021/9/10 10:55
 * @Description:
 * 实现Runnable创建线程
 **/
public class ThreadCreatTest2 {

    public static void main(String[] args) {
        System.out.println("开始执行方法:");

        ThreadRunnable threadRunnable = new ThreadRunnable();

        new Thread(threadRunnable).start();

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程:"+Thread.currentThread().getName()+i+"开始执行");
        }
    }
}
