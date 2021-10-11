package singleton;

/**
 * @Author: ljf
 * @Create: 2021/10/11 20:55
 * @Description: 静态内部类创建单例
 * JVM在加载外部类的时候,并不会立即加载内部类,而是当调用的时候再去加载,所以就类似之前的懒汉模式,而且JVM加载机制保证了线程安全,所以这种方式推荐使用.
 **/
public class SingletonObject6 {

    public SingletonObject6() {
    }

    private static class InstanceHolder{
        private final static SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance(){
        return InstanceHolder.instance;
    }
}
