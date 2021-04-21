package springStep.step7.code.beans.factory;

import springStep.step7.code.beans.BeanDefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public abstract class AbstractBeanFactory implements BeanFactory {

	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private final List<String> beanDefinitionNames = new ArrayList<String>();

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
        }
        return bean;
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

    /**
     * 初始化bean
     * @param beanDefinition
     * @return
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;

}
