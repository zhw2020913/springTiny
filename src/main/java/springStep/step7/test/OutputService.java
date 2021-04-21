package springStep.step7.test;


import org.junit.Assert;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 16:17
 */
public class OutputService {

    private HelloWorldService helloWorldService;

    public void output(String text){
        Assert.assertNotNull(helloWorldService);
        System.out.println(text);
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
}
