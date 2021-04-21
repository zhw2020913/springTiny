package springStep.step6.code.beans.factory;

import springStep.step6.code.beans.BeanDefinition;

/**
 * bean的容器
 *
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;


}
