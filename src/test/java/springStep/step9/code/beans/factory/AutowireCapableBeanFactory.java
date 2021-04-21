package springStep.step9.code.beans.factory;
import springStep.step9.code.BeanReference;
import springStep.step9.code.aop.BeanFactoryAware;
import springStep.step9.code.beans.BeanDefinition;
import springStep.step9.code.beans.PropertyValue;

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
			Object value = propertyValue.getValue();//获得bean 中propertyValue
			if (value instanceof BeanReference) {//主要是bean 之间的相互引用
				BeanReference beanReference = (BeanReference) value;
				//需要获取bean进行依赖注入时，才进行bean的创建
				//我们使用lazy-init的方式，将createBean的事情放到`getBean`的时候才执行
				value = getBean(beanReference.getName());
				//通过name 去创建bean,而创建bean的过程又调用applyPropertyValues，属于递归。

			}
			try {
				//System.out.println("********bean:"+bean.toString());
				Method declaredMethod = bean.getClass().getDeclaredMethod(
						"set" + propertyValue.getName().substring(0, 1).toUpperCase()
								+ propertyValue.getName().substring(1), value.getClass());
				//上面字符串的切割的目的就是为了将自符串变为驼峰式并加set 例如expression 变为setExpression
				//System.out.println("value.getClass()"+value.getClass());
				declaredMethod.setAccessible(true);
				//System.out.println("bean:"+bean+"******value:"+value);
				declaredMethod.invoke(bean, value);
			} catch (NoSuchMethodException e) {
				Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
				declaredField.setAccessible(true);
				declaredField.set(bean, value);
			}
		}
	}
}
