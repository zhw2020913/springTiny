package springStep.step2.code.factory;

import springStep.step2.code.BeanDefinition;
//方法进行抽象
public interface BeanFactory {

    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
