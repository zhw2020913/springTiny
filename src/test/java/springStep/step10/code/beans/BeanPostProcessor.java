package springStep.step10.code.beans;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 20:19
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;
    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
