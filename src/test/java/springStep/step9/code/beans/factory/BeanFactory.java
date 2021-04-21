package springStep.step9.code.beans.factory;

import springStep.step9.code.beans.BeanDefinition;

/**
 * bean的容器
 *
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

}
