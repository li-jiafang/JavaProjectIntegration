package thread.death.lock;

/**
 * @Author: ljf
 * @Create: 2021/9/11 11:30
 * @Description:
 **/
public class DeathLockTest {

    public static void main(String[] args) throws InterruptedException {

        DeathLock deathLock1 = new DeathLock();

        Thread thread1 = new Thread(deathLock1, "窗口1");
        Thread thread2 = new Thread(deathLock1, "窗口2");
        thread1.start();
        Thread.sleep(40);
        thread2.start();


    }
}
