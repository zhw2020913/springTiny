package springStep.step4.test.xml;

import org.junit.Assert;
import org.junit.Test;
import springStep.step4.code.BeanDefinition;
import springStep.step4.code.io.ResourceLoader;
import springStep.step4.code.xml.XmlBeanDefinitionReader;
import springStep.step4.test.HelloWorldService;

import java.io.IOException;
import java.util.Map;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 14:50
 */
public class XmlBeanDefinitionReaderTest {
    @Test
    public void test ()   {//测试封装xml 中的bean没有问题
        ResourceLoader resourceLoader = new ResourceLoader();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(resourceLoader);
        try {
            xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc4.xml");
            Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
            //HelloWorldService helloWorldService = (HelloWorldService) registry.get("helloWorldService").getBean();
            //helloWorldService.helloWorld(); 这种方法获得的HelloWorldService会产生空指针的情况，因为并未对bean实例化
            Assert.assertTrue(registry.size() > 0);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
