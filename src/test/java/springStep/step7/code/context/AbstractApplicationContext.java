package springStep.step7.code.context;


import springStep.step7.code.beans.BeanDefinition;
import springStep.step7.code.beans.factory.AbstractBeanFactory;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 17:42
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;
    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception{
    }

    @Override
    public Object getBean(String name) throws Exception {
        //System.out.println("getBean name:"+name);
        return beanFactory.getBean(name);
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanFactory.registerBeanDefinition(name, beanDefinition);
    }
}
