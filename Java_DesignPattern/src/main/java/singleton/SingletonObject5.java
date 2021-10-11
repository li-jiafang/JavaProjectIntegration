package singleton;

/**
 * @Author: ljf
 * @Create: 2021/10/11 20:52
 * @Description: volatile关键字+双重校验
 *
 * 这一种方式和第四种比 就加了一个 volatile 关键字.
 * volatile 关键字保证了在第二个线程来读的时候实例已经完全被写完(关于此关键字 后续会重点说明)
 **/
public class SingletonObject5 {

    private static volatile SingletonObject5 instance = null;

    public SingletonObject5() {
    }

    public static SingletonObject5 getInstance(){
        if (instance == null){
            synchronized (SingletonObject5.class){
                if (instance == null){
                    return new SingletonObject5();
                }
            }
        }
        return instance;
    }
}
