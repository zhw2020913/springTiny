package springStep.step3.test;

import org.junit.Test;
import springStep.step3.code.BeanDefinition;
import springStep.step3.code.PropertyValue;
import springStep.step3.code.PropertyValues;
import springStep.step3.code.factory.AutowireCapableBeanFactory;
import springStep.step3.code.factory.BeanFactory;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 11:05
 */
public class BeanFactoryTest {
    @Test
    public void test() throws Exception {
        //1.初始化beanFactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        //2.bean定义
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("springStep.step3.test.HelloWorldService");
        //3.设置属性
        PropertyValue propertyValue = new PropertyValue("text","hello world");
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(propertyValue);
        //4.生成bean
        beanDefinition.setPropertyValues(propertyValues);
        beanFactory.registerBeanDefinition("HelloWorldService",beanDefinition);
        //5.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("HelloWorldService");
        helloWorldService.helloWorld();

    }
}
