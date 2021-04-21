package springStep.step4.test;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 14:48
 */
public class HelloWorldService {
    private String text;

    public void helloWorld(){
        System.out.println(text);
    }

    public void setText(String text) {
        this.text = text;
    }
}
