package java.us.codecraft.tinyioc.aop;

import java.us.codecraft.tinyioc.beans.factory.BeanFactory;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 11:10
 */
//通过setBeanFactory方法实现bean 的获得以及注册
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
