package springStep.step8.code.beans.factory;
import springStep.step8.code.BeanReference;
import springStep.step8.code.beans.BeanDefinition;
import springStep.step8.code.beans.PropertyValue;

import java.lang.reflect.Field;

/**
 * 可自动装配内容的BeanFactory
 * 
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
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
				//System.out.println("value:"+value);
			}
			declaredField.set(bean, value);
		}
	}
}
