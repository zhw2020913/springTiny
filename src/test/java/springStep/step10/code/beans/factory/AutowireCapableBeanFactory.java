package springStep.step10.code.beans.factory;
import springStep.step10.code.BeanReference;
import springStep.step10.code.aop.BeanFactoryAware;
import springStep.step10.code.beans.BeanDefinition;
import springStep.step10.code.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 可自动装配内容的BeanFactory
 * 
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
	protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
		if (bean instanceof BeanFactoryAware) {//增加一种对是否为beanFactoryAware的判断
			((BeanFactoryAware) bean).setBeanFactory(this);
		}
		for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
			Object value = propertyValue.getValue();
			if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;
				//需要获取bean进行依赖注入时，才进行bean的创建
				//我们使用lazy-init的方式，将createBean的事情放到`getBean`的时候才执行
				value = getBean(beanReference.getName());
				//System.out.println("value:"+value);
			}
			try {
				Method declaredMethod = bean.getClass().getDeclaredMethod(
						"set" + propertyValue.getName().substring(0, 1).toUpperCase()
								+ propertyValue.getName().substring(1), value.getClass());
				declaredMethod.setAccessible(true);

				declaredMethod.invoke(bean, value);
			} catch (NoSuchMethodException e) {
				Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
				declaredField.setAccessible(true);
				declaredField.set(bean, value);
			}
		}
	}
}
