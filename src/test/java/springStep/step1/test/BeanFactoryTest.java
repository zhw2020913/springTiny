package springStep.step1.test;

import org.junit.Test;
import springStep.step1.code.BeanDefinition;
import springStep.step1.code.BeanFactory;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 10:14
 */
public class BeanFactoryTest {
    @Test
    public void test(){
        //1.初始化beanFactory
        BeanFactory beanFactory = new BeanFactory();
        //2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition(new HelloWorldService());//生成bean
        beanFactory.registerBeanDefinition("HelloWorldService",beanDefinition);//注册bean
        //3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("HelloWorldService");
        helloWorldService.helloWorld();
    }

}
