package statics.dynamic;

/**
 * @Author: ljf
 * @Create: 2021/9/20 14:48
 * @Description:
 **/
public class PersonDaoImpl implements PersonDao{
    @Override
    public void save() {
        System.out.println("执行业务逻辑");
    }
}
