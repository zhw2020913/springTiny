package springStep.step3.code.factory;



import springStep.step3.code.BeanDefinition;
import springStep.step3.code.PropertyValue;

import java.lang.reflect.Field;

/**
 * 可自动装配内容的BeanFactory
 * 
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		Object bean = createBeanInstance(beanDefinition);//创建类对象的实体
		applyPropertyValues(bean, beanDefinition);//对bean 中的一些属性值进行设置
		return bean;
	}

	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
		return beanDefinition.getBeanClass().newInstance();
	}

	protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
		for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
			//获得要设置值的类的属性属性declaredField=private java.lang.String springStep.step3.test.HelloWorldService.text
			Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
			//System.out.println("*****bean:"+bean);
			//System.out.println("*****declaredField:"+declaredField);
			//System.out.println("****propertyValue.getValue():"+propertyValue.getValue());
			declaredField.setAccessible(true);//允许设置值
			//propertyValue.getValue() 获得属性对应的值
			declaredField.set(bean, propertyValue.getValue());
		}
	}
}
