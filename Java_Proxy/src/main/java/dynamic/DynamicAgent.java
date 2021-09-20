package dynamic;

import java.lang.reflect.Proxy;

/**
 * @Author: ljf
 * @Create: 2021/9/20 14:47
 * @Description:
 **/
public class DynamicAgent {

    public static void main(String[] args) {
        // 1. 创建被代理的对象，UserService接口的实现类
        PersonDao personDao = new PersonDaoImpl();

        // 2. 创建一个将传给代理类的调用请求处理器，处理所有的代理对象上的方法调用
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl();
        // 设置目标对象
        invocationHandler.setTarget(personDao);
        // 获取对应的 ClassLoader
        ClassLoader classLoader = personDao.getClass().getClassLoader();
        // 获取所有的接口
        Class<?>[] interfaces = personDao.getClass().getInterfaces();

        // 动态获取代理类
        /**
         * a.JDK会通过根据传入的参数信息动态地在内存中创建和.class 文件等同的字节码
         * b.然后根据相应的字节码转换成对应的class，
         * c.然后调用newInstance()创建代理实例
         */
        PersonDao proxyInstance = (PersonDao) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        // 代理类执行方法
        proxyInstance.save();
    }
}
