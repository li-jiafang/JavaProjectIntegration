package thread.local;

/**
 * @Author: ljf
 * @Create: 2021/9/11 18:10
 * @Description:
 **/
public class ThreadLocal1 extends Thread{

    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            threadLocal.set(i);
            System.out.println(Thread.currentThread().getName()+"====="+getThreadLocal());
        }

    }

    public Integer getThreadLocal(){
        return threadLocal.get();
    }
}
