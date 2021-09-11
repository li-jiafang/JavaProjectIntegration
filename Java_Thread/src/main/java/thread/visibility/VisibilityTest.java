package thread.visibility;

/**
 * @Author: ljf
 * @Create: 2021/9/11 22:44
 * @Description:
 *
 * 线程可见性测试
 **/
public class VisibilityTest {

    public static void main(String[] args) throws InterruptedException {
        Visibility visibility = new Visibility();
        visibility.start();
        Thread.sleep(3000);
        visibility.setRunning();
        System.out.println("flag已经修改为false");
        Thread.sleep(1000);

        System.out.println("flag="+visibility.flag);
    }
}
