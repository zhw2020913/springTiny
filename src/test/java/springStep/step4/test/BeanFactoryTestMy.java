package springStep.step4.test;

import org.junit.Test;
import springStep.step4.code.BeanDefinition;
import springStep.step4.code.factory.AutowireCapableBeanFactory;
import springStep.step4.code.factory.BeanFactory;
import springStep.step4.code.io.ResourceLoader;
import springStep.step4.code.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/21 15:59
 */
public class BeanFactoryTestMy {
    @Test
    public void test() throws Exception {
        //1.创建bean容器工厂
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        //2.读取xml中的bean的值
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc4.xml");
        //3.通过bean工厂注册bean
        for (Map.Entry<String, BeanDefinition> beanDefinitionMap:xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionMap.getKey(),beanDefinitionMap.getValue());
        }
        //4.获得bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
