package java.us.codecraft.tinyioc.beans.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.us.codecraft.tinyioc.BeanReference;
import java.us.codecraft.tinyioc.beans.AbstractBeanDefinitionReader;
import java.us.codecraft.tinyioc.beans.BeanDefinition;
import java.us.codecraft.tinyioc.beans.PropertyValue;
import java.us.codecraft.tinyioc.beans.io.ResourceLoader;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/9 16:35
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }
    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }
    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        //javax.xml.parsers 包DocumentBuilderFactory创建DOM模式的解析器对象,
        // DocumentBuilderFactory是抽象工厂类，不能直接实例化，但是有newInstance方法
        //DocumentBuilderFactory.newInstance() 得到创建 DOM 解析器的工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //factory.newDocumentBuilder方法得到 DOM 解析器对象
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        //DOM解析器解析输入流，这里可以是XML，文档转化为输入流，
        // 或者字符串转为ByteArrayInputStream,DOM 解析器对象的 parse() 方法解析 XML 文档，
        // 得到代表整个文档的 Document 对象
        Document doc = docBuilder.parse(inputStream);
        // 解析bean
        registerBeanDefinitions(doc);
        inputStream.close();
    }

    public void registerBeanDefinitions(Document doc) {
        //得到 XML 文档的根节点
        Element root = doc.getDocumentElement();

        parseBeanDefinitions(root);
    }

    protected void parseBeanDefinitions(Element root) {
        //得到根节点的子节点
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            //instanceof 是 Java 的一个二元操作符，类似于 ==，>，< 等操作符。
            //它的作用是测试它左边的对象是否是它右边的类的实例，返回 boolean 的数据类型
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    protected void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        //处理属性并进行封装
        processProperty(ele,beanDefinition);
        beanDefinition.setBeanClassName(className);
        //直接调用父类的方法进行bean的注册
        getRegistry().put(name, beanDefinition);
    }

    private void processProperty(Element ele,BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {//如果是ref
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    //封装到bean 对应的PropertyValue 对象中
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
