package springStep.step2.test;

import org.junit.Test;
import springStep.step2.code.BeanDefinition;
import springStep.step2.code.factory.AutowireCapableBeanFactory;
import springStep.step2.code.factory.BeanFactory;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/21 14:29
 */
public class BeanFactoryMy {
    @Test
    public void testBeanFactory(){
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass(HelloWorldService.class);//与第一步不同的是bean自动创建
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        beanFactory.registerBeanDefinition("helloWorldService",beanDefinition);
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
