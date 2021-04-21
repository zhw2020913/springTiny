package springStep.step4.code.factory;

import springStep.step4.code.BeanDefinition;

/**
 * bean的容器
 *
 */
public interface BeanFactory {

    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
