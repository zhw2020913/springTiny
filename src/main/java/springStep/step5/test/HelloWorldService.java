package springStep.step5.test;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 14:48
 */
public class HelloWorldService {
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
