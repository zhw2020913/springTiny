package springStep.step3.test;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 10:14
 */
public class HelloWorldService {
    private String text;
    private int n ;
    public void setN(int n) {
        this.n = n;
    }

    public void helloWorld(){
        System.out.println(text);
    }

    public void setText(String text) {
        this.text = text;
    }
}
