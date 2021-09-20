package cglib;

/**
 * @Author: ljf
 * @Create: 2021/9/20 15:41
 * @Description:
 **/
public class CglibProxy {

    public static void main(String[] args) {
        // 目标对象
        DeptDao deptDao = new DeptDao();

        // 设置目标对象
        MethodInterceptorImpl methodInterceptor = new MethodInterceptorImpl();
        methodInterceptor.setTarget(deptDao);

        // 设置代理对象
        DeptDao proxy = (DeptDao) methodInterceptor.getProxyInstance();

        // 代理对象执行方法
        proxy.save();
    }
}
