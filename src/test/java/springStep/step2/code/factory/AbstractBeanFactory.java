package springStep.step2.code.factory;



import springStep.step2.code.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//将BeanFactory 接口和数据整合,并方便增加方法
public abstract class AbstractBeanFactory implements BeanFactory {

	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	@Override
    public Object getBean(String name) {
		return beanDefinitionMap.get(name).getBean();
	}

	@Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
	    //先创建一个bean,就是根据beanDefinition中class的地址创建一个对象
        Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name, beanDefinition);
	}

    /**
     * 初始化bean
     * @param beanDefinition
     * @return
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition);

}
