package thread.test;

import thread.creat.ThreadCreat;

/**
 * @Author: ljf
 * @Create: 2021/9/10 10:46
 * @Description:
 * 创建Thread类开启线程
 **/
public class ThreadCreatTest1 {

    public static void main(String[] args) {

        System.out.println("开始执行方法:");

        ThreadCreat threadCreat = new ThreadCreat();
        threadCreat.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程:"+Thread.currentThread().getName()+i+"开始执行");
        }
    }
}
