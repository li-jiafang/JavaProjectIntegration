package thread.test;

/**
 * @Author: ljf
 * @Create: 2021/9/10 10:57
 * @Description:
 *
 * 匿名内部类创建线程
 **/
public class ThreadCreatTest3 {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("子线程:"+Thread.currentThread().getName()+i+"开始执行");
                }
            }
        });
        thread.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程:"+Thread.currentThread().getName()+i+"开始执行");
        }

    }
}
