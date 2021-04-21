package springStep.step6.test;


/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 16:17
 */
public class OutputService {

    //类之间的属性引用
    //private HelloWorldService helloWorldService;
    private String out;

    public void output(String text){

        System.out.println(text);

    }
    public void setOut(String out) {
        this.out = out;
    }


}
