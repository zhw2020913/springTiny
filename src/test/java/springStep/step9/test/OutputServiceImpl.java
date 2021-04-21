package springStep.step9.test;

import org.junit.Assert;


/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 11:15
 */
public class OutputServiceImpl implements OutputService{
    /*private HelloWorldService helloWorldService;

    public HelloWorldService getHelloWorldService() {
        return helloWorldService;
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }*/

    @Override
    public void output(String text) {
        //Assert.assertNotNull(helloWorldService);
        System.out.println(text);
    }
}
