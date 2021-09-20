package statics;

/**
 * @Author: ljf
 * @Create: 2021/9/20 11:26
 * @Description:
 **/
public class IUserDaoProxy implements IUserDao{

    private IUserDao target;

    public void setTarget(IUserDao userDao){
        this.target = userDao;
    }



    @Override
    public void save() {
        System.out.println("静态代理代理开始");

        target.save();

        System.out.println("静态代理代理结束");
    }
}
