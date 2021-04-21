package springStep.step5.test;

import org.junit.Test;
import springStep.step5.code.BeanDefinition;
import springStep.step5.code.factory.AutowireCapableBeanFactory;
import springStep.step5.code.factory.BeanFactory;
import springStep.step5.code.io.ResourceLoader;
import springStep.step5.code.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/21 16:39
 */
public class BeanFactoryTestMy {
    @Test
    public void test() throws Exception {
        //1.读取xml文件加载bean
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc5.xml");
        //2.创建bean加载工厂
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        //3.注册bean
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry:xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }
        //4.获得bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }


}
