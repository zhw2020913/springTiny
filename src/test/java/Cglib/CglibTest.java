package Cglib;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 14:39
 */
public class CglibTest {
    @Test
    public void testCglib() {
        //创建Dao代理
        DaoProxy daoProxy = new DaoProxy();
        //Enhancer是cglib中使用频率很高的一个类，它是一个字节码增强器，可以用来为无接口的类创建代理。
        //它的功能与java自带的Proxy类挺相似的。它会根据某个给定的类创建子类，并且所有非final的方法都带有回调钩子。
        Enhancer enhancer = new Enhancer();
        //需要自动创建的类
        enhancer.setSuperclass(Dao.class);
        //为创建类增加的代理方法
        enhancer.setCallback(daoProxy);
        //需要代理的dao是由enhancer主动创建的
        Dao dao = (Dao)enhancer.create();
        dao.update();
        dao.select();
    }

}
