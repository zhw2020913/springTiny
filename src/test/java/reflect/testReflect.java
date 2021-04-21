package reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 17:30
 */
public class testReflect {
    @Test
    public void positiveReflect(){
        Apple apple = new Apple(); //直接初始化，「正射」
        apple.setPrice(4);
        System.out.println(apple.getPrice());
    }
    @Test
    public void antiReflect() throws Exception {//反射
        //而反射则是一开始并不知道我要初始化的类对象是什么，自然也无法使用 new 关键字来创建对象了。(和控制反转差不多)
        //这时候，我们使用 JDK 提供的反射 API 进行反射调用
        Class clz = Class.forName("reflect.Apple");//找类
        Method method = clz.getMethod("setPrice", float.class);//找方法
        Constructor constructor = clz.getConstructor();//获得类
        //使用 Constructor 对象的 newInstance 方法获取反射类对象
        Object object = constructor.newInstance();//创造类
        method.invoke(object, 4);//向的对象中调用相应的方法并传值
        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println("Apple Price:" + getPriceMethod.invoke(object));

    }
}
