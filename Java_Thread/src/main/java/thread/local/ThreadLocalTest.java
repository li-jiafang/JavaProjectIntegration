package thread.local;

/**
 * @Author: ljf
 * @Create: 2021/9/11 16:59
 * @Description:
 **/
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal1 threadLocal1 = new ThreadLocal1();
        Thread thread1 = new Thread(threadLocal1,"线程1");
        Thread thread2 = new Thread(threadLocal1,"线程2");
        thread1.start();
        thread2.start();

    }
}
