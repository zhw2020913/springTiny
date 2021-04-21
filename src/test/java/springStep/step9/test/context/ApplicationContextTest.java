package springStep.step9.test.context;

import org.junit.Test;
import springStep.step9.code.context.ApplicationContext;
import springStep.step9.code.context.ClassPathXmlApplicationContext;
import springStep.step9.test.HelloWorldService;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 11:33
 */
public class ApplicationContextTest {
    @Test
    public void  test() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc9.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
    @Test
    public void testPostBeanProcessor() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc9-postbeanprocessor.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
