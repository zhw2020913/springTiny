package springStep.step6.test;

import org.junit.Test;
import springStep.step6.code.context.ApplicationContext;
import springStep.step6.code.context.ClassPathXmlApplicationContext;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 15:01
 */
public class ApplicationContextTest {
    @Test
    public void contextTest() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc6.xml");
        //获得bean
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }

}
