package singleton;


/**
 * 单例模式创建规则
 * 1. 单例模式保证一个类仅有一个实例
 * 2. 单例模式必须自己创建自己的唯一实例
 * 3. 单例模式提供一个静态方法来获取(一般是getInstance()方法)
 */

/**
 * 单例的创建方式
 * 1. 饿汉式
 * 2. 懒汉式
 * 3. 懒汉式加synchronize同步
 * 4. 双重校验
 * 5. Volatile+双重校验
 * 6. 静态内部类
 * 7. 枚举
 */

/**
 * @Author: ljf
 * @Create: 2021/10/11 19:34
 * @Description: 饿汉式
 * 饿汉式的创建方法关键在于 instance作为类变量直接得到了初始化，这种方法的优点在于多线程环境下能够百分百地保证同步，
 * 在多线程环境下不可能被实例化两次，但是instance若是被加载后很长一段时间后才使用，就意味着instance实例开辟的堆内
 * 存会驻留更长的时间，所以更优的创建方式应是伴随着懒加载的。
 **/
public class SingletonObject1 {

    private static final SingletonObject1 instance = new SingletonObject1();

    public SingletonObject1() {
    }

    public static SingletonObject1 getInstance(){
        return instance;
    }

}
