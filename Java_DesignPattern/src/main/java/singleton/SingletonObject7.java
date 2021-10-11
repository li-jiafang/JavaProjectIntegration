package singleton;

/**
 * @Author: ljf
 * @Create: 2021/10/11 20:58
 * @Description: 枚举设置单例
 **/
public class SingletonObject7 {

    private SingletonObject7() {
    }

    private enum Singleton {
        INSTANCE;
        private final SingletonObject7 instance;

        Singleton() {
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance() {
            return instance;
        }
    }

    public static SingletonObject7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
