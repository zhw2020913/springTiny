package springStep.step6.code.beans;

/**
 * 从配置中读取BeanDefinitionReader
 * 主要目的读取配置文件比如：xml
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
