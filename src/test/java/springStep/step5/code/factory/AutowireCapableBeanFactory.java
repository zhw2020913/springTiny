package springStep.step5.code.factory;
import springStep.step5.code.BeanDefinition;
import springStep.step5.code.BeanReference;
import springStep.step5.code.PropertyValue;

import java.lang.reflect.Field;

/**
 * 可自动装配内容的BeanFactory
 * 
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		Object bean = createBeanInstance(beanDefinition);//创建完bean ,才进行引用注入依赖
		beanDefinition.setBean(bean);
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
				//在xml读取时可能是ref就是bean 之间的相互引用，这个时候的beanReference对象中仅仅只有name属性

				BeanReference beanReference = (BeanReference) value;
				//需要获取bean进行依赖注入时，才进行bean的创建
				//我们使用lazy-init的方式，将createBean的事情放到`getBean`的时候才执行
				value = getBean(beanReference.getName());
			}
			//为bean的实例对象中相应的属性值设置相应的值
			declaredField.set(bean, value);
		}
	}
}
