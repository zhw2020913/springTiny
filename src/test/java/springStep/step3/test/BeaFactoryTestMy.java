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
 * @date 2021/4/21 14:59
 */
public class BeaFactoryTestMy {
    @Test
    public void testSetPropertyValue() throws Exception {
        //1.创建容器工厂
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        //2.创建承载bean的对象beanDefinition容器
        BeanDefinition beanDefinition = new BeanDefinition();
        //3.设置bean对应的类的路径
        beanDefinition.setBeanClassName("springStep.step3.test.HelloWorldService");
        //4.设置bean中属性对应的值
        PropertyValue propertyValue = new PropertyValue("text","hello,spring!");
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(propertyValue);
        beanDefinition.setPropertyValues(propertyValues);
        //5.容器工厂注册bean
        beanFactory.registerBeanDefinition("helloWorldService",beanDefinition);
        //6.获得注册的bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();

    }
}
