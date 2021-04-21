package java.us.codecraft.tinyioc.beans.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.us.codecraft.tinyioc.BeanReference;
import java.us.codecraft.tinyioc.aop.BeanFactoryAware;
import java.us.codecraft.tinyioc.beans.BeanDefinition;
import java.us.codecraft.tinyioc.beans.PropertyValue;

/**
 * @author yihua.huang@dianping.com
 */
//可自动装配内容的BeanFactory
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    /*protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        //mbd.getPropertyValues().getPropertyValues()
        // 先获得mbd类上PropertyValues 属性，再获得一个装有PropertyValue 的list
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
            //getDeclaredField()
            //获取一个类的 ==所有成员变量，不包括基类== 。
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            //java 反射机制 之 getDeclaredField 获取私有保护字段,
            // 再setAccessible(true)取消对权限的检查 实现对私有的访问和赋值
            declaredField.setAccessible(true);
            //将指定对象变量上此 Field 对象表示的字段设置为指定的新值.(就是指定对象对应的属性字段设置新的值)
            declaredField.set(bean, propertyValue.getValue());
        }
    }*/
    //第九步骤内容发生变化
    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);

                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }



}
