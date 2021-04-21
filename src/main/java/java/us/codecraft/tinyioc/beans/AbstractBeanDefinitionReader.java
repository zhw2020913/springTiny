package java.us.codecraft.tinyioc.beans;

import java.us.codecraft.tinyioc.beans.io.ResourceLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/9 16:09
 */
//从配置文件中读取BeanDefinitionReader
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private Map<String,BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<String, BeanDefinition>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
