package singleton;

/**
 * @Author: ljf
 * @Create: 2021/10/11 20:46
 * @Description: 双重校验创建单例模式
 * 这种方式解决了第三种模式将锁加到方法上性能差的问题,当判断实例存在的时候直接返回创建好的对象,而不是判断锁的状态.
 * 就算有两个线程同时判断 instance 为null,但是new实例的过程中加了锁,也会只有一个线程会创建实例对象,但是如果在
 * 实际开发中,如果此实例的内部属性很多,实例化需要很长时间时,当对象还在实例化的时候,如果有第二个线程通过getInstance()
 * 方法来取此实例,就会取到null值.
 **/
public class SingletonObject4 {

    private static SingletonObject4 instance = null;

    public SingletonObject4() {
    }

    public static SingletonObject4 getInstance(){
        if (instance == null){
            synchronized (SingletonObject4.class){
                if (instance == null){
                    return new SingletonObject4();
                }
            }
        }
        return instance;
    }
}
