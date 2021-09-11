package thread.visibility;

/**
 * @Author: ljf
 * @Create: 2021/9/11 22:44
 * @Description:
 **/
public class Visibility extends Thread{
    public volatile Boolean flag = true;

    @Override
    public void run() {
        System.out.println("线程开始执行");
        while (flag){
            //System.out.println("正在执行中");
        }
        System.out.println("线程结束执行");
    }
    public void setRunning(){
        this.flag = false;
    }
}
