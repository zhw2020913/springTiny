package springStep.step1.code;

//`BeanDefinition`来封装了bean对象,可以保存一些额外信息
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}
