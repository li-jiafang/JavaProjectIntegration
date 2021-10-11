package singleton;

/**
 * @Author: ljf
 * @Create: 2021/10/11 20:43
 * @Description: 懒汉式+synchronize同步方法
 *
 * 这种方法通过添加同步控制既满足了懒加载，又满足了instance实例的唯一性，但是，添加了同步控制后getInstance方法的调用是串行化的，
 * 效率较低，因此引出第四种创建方式---Double Check方式
 **/
public class SingletonObject3 {

    private static SingletonObject3 instance = null;

    public SingletonObject3() {
    }

    public synchronized static SingletonObject3 getInstance(){
        if (null == instance){
            return new SingletonObject3();
        }
        return instance;
    }
}
