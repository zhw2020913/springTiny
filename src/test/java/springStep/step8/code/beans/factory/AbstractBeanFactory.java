package springStep.step8.code.beans.factory;

import springStep.step8.code.beans.BeanDefinition;
import springStep.step8.test.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public abstract class AbstractBeanFactory implements BeanFactory {

	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private final List<String> beanDefinitionNames = new ArrayList<String>();
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();//第8步增加
	@Override
    public Object getBean(String name) throws Exception {

        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        //bean 还没有进行创建
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
            initializeBean(bean,name);

        }
        return bean;
	}
	//第8步增加
    protected void initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor: beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean,name);
        }
        //缺少去调用初始化的方法
        // TODO:call initialize method
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
    }
    //第8步增加 用于创建一个bean 对应的实例
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        //创建类的实例对象
        //System.out.println("创建类的实例对象:"+beanDefinition.getBeanClass());
        return beanDefinition.getBeanClass().newInstance();
    }
    //第8步增加
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);//第五步增加的方法
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
    }
    //基本所有的属性都需要set方法
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
        this.beanPostProcessors.add(beanPostProcessor);
    }
    //第8步增加
    public List getBeansForType(Class type) throws Exception {
	    List beans = new ArrayList<Object>();
        for (String beanDefinitionName: beanDefinitionNames) {
            //判断isAssignableFrom括号中的参数是不是可以转化为type类的class(就是向下转型)或者同类型
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
	@Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        //仅仅进行注册，还没有进行beanDefinition 内部bean的封装
	    beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }
    //通过循环对bean进行初始化
    public void preInstantiateSingletons() throws Exception {//
        for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }
}
