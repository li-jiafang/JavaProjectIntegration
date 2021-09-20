package statics;

/**
 * @Author: ljf
 * @Create: 2021/9/20 11:23
 * @Description:
 **/
public class StaticProxy {

    public static void main(String[] args) {

        IUserDaoImpl iUserDao = new IUserDaoImpl();

        IUserDaoProxy iUserDaoProxy = new IUserDaoProxy();

        iUserDaoProxy.setTarget(iUserDao);

        iUserDaoProxy.save();


    }
}
