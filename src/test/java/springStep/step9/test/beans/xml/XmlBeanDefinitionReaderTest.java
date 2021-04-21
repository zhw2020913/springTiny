package springStep.step9.test.beans.xml;

import org.junit.Assert;
import org.junit.Test;
import springStep.step9.code.beans.BeanDefinition;
import springStep.step9.code.beans.io.ResourceLoader;
import springStep.step9.code.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 11:30
 */
public class XmlBeanDefinitionReaderTest {
    @Test
    public void test() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc9.xml");
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        Assert.assertTrue(registry.size() > 0);
    }
}
