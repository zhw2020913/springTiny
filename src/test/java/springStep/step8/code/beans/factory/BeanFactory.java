package springStep.step8.code.beans.factory;

import springStep.step8.code.beans.BeanDefinition;

/**
 * bean的容器
 *
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
