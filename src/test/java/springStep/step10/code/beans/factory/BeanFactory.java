package springStep.step10.code.beans.factory;

/**
 * bean的容器
 *
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

}
