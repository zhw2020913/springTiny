package java.us.codecraft.tinyioc.beans;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/12 16:59
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
