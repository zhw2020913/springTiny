package java.us.codecraft.tinyioc.beans;
//bean的内容及元数据，保存在BeanFactory中，包装bean的实体
/**
 * @author yihua.huang@dianping.com
 */
public class BeanDefinition {
    //仅创建一个object 用于存储数据
    private Object bean;//相当于类实例

    private Class beanClass;//类的class

    private String beanClassName;//类的地址

    private PropertyValues propertyValues;//类的属性赋值以及ref 中方法的引用

    public BeanDefinition() {
    }


    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object getBean() {
        return bean;
    }
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

}
