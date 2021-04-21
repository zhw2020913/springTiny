package java.us.codecraft.tinyioc.context;

import java.us.codecraft.tinyioc.beans.BeanDefinition;
import java.us.codecraft.tinyioc.beans.BeanPostProcessor;
import java.us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;
import java.util.List;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/12 10:41
 */
public abstract class AbstractApplicationContext implements ApplicationContext{
    protected AbstractBeanFactory beanFactory;
    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    //不同于BeanDefinitionReader 接口中的方法
    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;
    public void refresh() throws Exception{
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }
    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }
    protected void onRefresh() throws Exception{
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
