package springStep.step2.test;

import org.junit.Test;
import springStep.step2.code.BeanDefinition;
import springStep.step2.code.factory.AutowireCapableBeanFactory;
import springStep.step2.code.factory.BeanFactory;


/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 10:14
 */
public class BeanFactoryTest {
    @Test
    public void test(){
        //1.初始化beanFactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        //2.注入bean,通过方法调用生成实体类并封装到Map对象中
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("springStep.step2.test.HelloWorldService");
        beanFactory.registerBeanDefinition("HelloWorldService",beanDefinition);
        //3.获取bean,通过Map获得对应的beanDefinition,然后获得实体类
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("HelloWorldService");
        helloWorldService.helloWorld();

    }

}
