package springStep.step4.test;

import org.junit.Assert;
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
 * @date 2021/4/14 14:49
 */
public class BeanFactoryTest {
    @Test
    public void test() throws Exception {
        // 1.读取配置xmL
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc4.xml");
        //2.初始化BeanFactory并注册bean
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        //entrySet是 java中 键-值 对的集合，Set里面的类型是Map.Entry，一般可以通过map.entrySet()得到
        //一种遍历,map键值的方法
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
        //3.获取bean
        //这个地方的name 要与bean 中name 保持一致
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }

}
