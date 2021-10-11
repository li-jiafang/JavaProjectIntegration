package singleton;

/**
 * @Author: ljf
 * @Create: 2021/10/11 19:34
 * @Description: 懒汉式
 * 所谓懒汉式就是在使用的时候才去创建，这样就可以避免类在初始化时提前创建，但是这种方式有一个很大的缺点，
 * 在多线程的环境下，若一开始因为线程上下文切换的原因，两个线程都通过了null==instance的if循环，这样就
 * 是new出两个实例，无法保证单例的唯一性，所以有下面第三种方法。
 **/
public class SingletonObject2 {
    private static SingletonObject2 instance = null;

    public SingletonObject2(){

    }

    public static SingletonObject2 getInstance(){
        if (instance == null){
            return new SingletonObject2();
        }
        return instance;
    }

}
