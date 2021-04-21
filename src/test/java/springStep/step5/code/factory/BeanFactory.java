package springStep.step5.code.factory;

import springStep.step5.code.BeanDefinition;

/**
 * bean的容器
 *
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
