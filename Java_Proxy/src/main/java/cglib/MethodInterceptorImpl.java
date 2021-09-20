package cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: ljf
 * @Create: 2021/9/20 15:42
 * @Description:
 **/
public class MethodInterceptorImpl implements MethodInterceptor {
    /**
     * 设置目标对象
     */
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    /**
     * 生成代理类
     * @return
     */
    public Object getProxyInstance(){
        // 调用工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        //创建代理类
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("开启业务逻辑 前");

        Object invoke = method.invoke(target, args);

        System.out.println("开启业务逻辑 后");

        return invoke;
    }
}
