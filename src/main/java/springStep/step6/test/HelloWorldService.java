package springStep.step6.test;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 14:48
 */
public class HelloWorldService {
    //两者相互依赖
    private String text;
    private OutputService outputService;

    public void helloWorld(){
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
