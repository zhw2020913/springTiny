package springStep.step3.code.factory;


import springStep.step3.code.BeanDefinition;

/**
 * bean的容器工厂
 *
 */
public interface BeanFactory {

    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
