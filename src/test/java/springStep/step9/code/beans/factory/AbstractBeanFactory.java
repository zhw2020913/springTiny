package springStep.step9.code.beans.factory;


import springStep.step9.code.beans.BeanDefinition;
import springStep.step9.code.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public abstract class AbstractBeanFactory implements BeanFactory {
    //相当于一个bean容器，其中beanDefinitionMap用于存储已注册的BeanDefinition通过键值对的方式提高效率。
    //当需要bean的时候直接从容器中提取即可，不需要提前注册。

	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private final List<String> beanDefinitionNames = new ArrayList<String>();
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();//第8步增加
	@Override
    public Object getBean(String name) throws Exception {

        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("没有bean 名字是" + name + "的bean 被定义");
        }
        Object bean = beanDefinition.getBean();
        //bean 还没有进行创建
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean,name);
        }
        return bean;
	}
	//第8步增加 用于增加bean创建后的一些封装性的一些操作
    protected Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor: beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean,name);
        }
        //缺少去调用初始化的方法
        // TODO:call initialize method
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
            //这个地方就会设置通知或者拦截器,以及生成aop代理建立bean与aop之间的联系。
        }
       // System.out.println("initializeBean:"+bean);
        return bean;
    }
    //第8步增加 用于创建一个bean 对应的实例
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        //创建类的实例对象
        //System.out.println("创建类的实例对象:"+beanDefinition.getBeanClass());
        return beanDefinition.getBeanClass().newInstance();
    }//第九步增加
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
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
    public List getBeansForType(Class type) throws Exception {//获得指定类型的bean
	    List beans = new ArrayList<Object>();
        for (String beanDefinitionName: beanDefinitionNames) {
            //判断isAssignableFrom括号中的参数是不是可以转化为type类的class(就是向下转型)或者同类型
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
    //通过循环对bean进行初始化
    public void preInstantiateSingletons() throws Exception {//
        for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }
}
