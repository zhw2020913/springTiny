package springStep.step8.code.context;


import springStep.step8.code.beans.factory.AbstractBeanFactory;
import springStep.step8.test.BeanPostProcessor;

import java.util.List;

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
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }
    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;
    //第8步增加方法
    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);//判断bean对应class类型
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }
    //用于初始化创建bean
    protected void onRefresh() throws Exception{
        beanFactory.preInstantiateSingletons();//第五步中直接设置beanDifinition中的bean已经出错
    }
    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }


}
