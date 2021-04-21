package springStep.step1.code;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//容器(`BeanFactory`),主要是封装对bean的一些操作
public class BeanFactory {

	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	public Object getBean(String name) {
		return beanDefinitionMap.get(name).getBean();
	}

	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(name, beanDefinition);
	}

}
