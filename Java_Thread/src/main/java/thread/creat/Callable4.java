package thread.creat;

import java.util.concurrent.Callable;

/**
 * @Author: ljf
 * @Create: 2021/9/10 11:08
 * @Description:
 **/
public class Callable4 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int ii = 0;
        for (int i = 0; i < 100; i++) {
            ii = i;
            System.out.println("子线程:"+Thread.currentThread().getName()+i+"开始执行");
        }
        return ii;
    }
}
