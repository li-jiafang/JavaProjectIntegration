package statics;

/**
 * @Author: ljf
 * @Create: 2021/9/20 11:24
 * @Description:
 **/
public class IUserDaoImpl implements IUserDao{
    @Override
    public void save() {
        System.out.println("执行保存业务逻辑");
    }
}
