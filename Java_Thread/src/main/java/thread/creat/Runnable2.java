package thread.creat;

/**
 * @Author: ljf
 * @Create: 2021/9/10 10:49
 * @Description:
 **/
public class Runnable2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("子线程:"+Thread.currentThread().getName()+i+"开始执行");
        }
    }
}
