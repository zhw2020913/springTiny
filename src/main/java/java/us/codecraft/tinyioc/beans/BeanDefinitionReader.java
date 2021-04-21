package java.us.codecraft.tinyioc.beans;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/9 11:42
 */
//从配置中读取BeanDefinitionReader
public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;

}
