package springStep.step5.code;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/14 15:43
 */
//解决bean 到bean 之间相互依赖注入
public class BeanReference {
    private String name;

    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
