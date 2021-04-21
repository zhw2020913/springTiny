package springStep.step6.test;

import org.junit.Test;
import springStep.step6.code.context.ApplicationContext;
import springStep.step6.code.context.ClassPathXmlApplicationContext;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/21 20:39
 */
public class ApplicationCintextMyTest {
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("tinyioc6.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
