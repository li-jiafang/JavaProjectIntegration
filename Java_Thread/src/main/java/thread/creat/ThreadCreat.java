package thread.creat;

/**
 * @Author: ljf
 * @Create: 2021/9/10 10:46
 * @Description:
 **/
public class ThreadCreat extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("子线程:"+Thread.currentThread().getName()+i+"开始执行");
        }
    }
}
