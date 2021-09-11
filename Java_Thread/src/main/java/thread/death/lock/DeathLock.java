package thread.death.lock;

/**
 * @Author: ljf
 * @Create: 2021/9/11 11:31
 * @Description:
 * 死锁原因:t1和t2线程互相持有对方的资源,都等待对方释放锁,最终导致死锁
 **/
public class DeathLock implements Runnable {

    private static int count = 100;
    private static String A = "A";
    private static String B = "B";

    @Override
    public void run() {
        synchronized (A) {
            synchronized (B) {
                safe();
            }
        }
    }
    public void safe() {
        synchronized (B) {
            synchronized (A) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + "售卖了第" + (100 - count + 1) + "票");
                    count--;
                }
            }
        }
    }
}


