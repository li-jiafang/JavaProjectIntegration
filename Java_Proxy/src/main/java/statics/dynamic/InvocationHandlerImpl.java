package statics.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: ljf
 * @Create: 2021/9/20 14:54
 * @Description:
 **/
public class InvocationHandlerImpl implements InvocationHandler {

    private Object target;


    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理扩展业务前");
        Object invoke = method.invoke(target, args);
        System.out.println("jdk动态代理扩展业务后");
        return invoke;
    }
}
