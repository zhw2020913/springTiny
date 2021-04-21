package springStep.step6.code.beans.factory;
import springStep.step6.code.beans.BeanDefinition;
import springStep.step6.code.BeanReference;
import springStep.step6.code.beans.PropertyValue;

import java.lang.reflect.Field;

/**
 * 可自动装配内容的BeanFactory
 * 
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		Object bean = createBeanInstance(beanDefinition);
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}

	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
		//创建类的实例对象
		//System.out.println("创建类的实例对象:"+beanDefinition.getBeanClass());

		return beanDefinition.getBeanClass().newInstance();
	}

	protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
		for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
			Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
			declaredField.setAccessible(true);
			Object value = propertyValue.getValue();
			if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;
				//需要获取bean进行依赖注入时，才进行bean的创建
				//我们使用lazy-init的方式，将createBean的事情放到`getBean`的时候才执行
				value = getBean(beanReference.getName());
			}
			declaredField.set(bean, value);
		}
	}
}
