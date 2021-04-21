package springStep.step10.test;


/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 11:15
 */
public class OutputServiceImpl implements OutputService {

    @Override
    public void output(String text) {
        //Assert.assertNotNull(helloWorldService);
        System.out.println(text);
    }
}
