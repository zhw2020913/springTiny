package springStep.step9.code.aop;

import org.aopalliance.intercept.MethodInterceptor;
import springStep.step9.code.beans.factory.AbstractBeanFactory;

import springStep.step9.code.beans.factory.BeanFactory;
import springStep.step9.code.beans.BeanPostProcessor;
import java.util.List;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 10:05
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {
    private AbstractBeanFactory beanFactory;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
       // System.out.println("调用postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        //System.out.println("调用postProcessAfterInitialization");
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }

        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        System.out.println("AspectJExpressionPointcutAdvisor的bean数量："+advisors.size());
        //获得容器中所有的注册的AspectJExpressionPointcutAdvisor类型的bean
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {//如果xml中存在bean 为
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {//切入面匹配到对应的类，先到类后到方法
                AdvisedSupport advisedSupport = new AdvisedSupport();//创建一个包含目标资源，拦截器，方法匹配的实体类对象
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                //问题点：为啥Advice能直接转化为MethodInterceptor？ Interceptor接口继承Advice接口，MethodInterceptor接口又继承Interceptor接口
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);//封装AdvisedSupport完成

                return new JdkDynamicAopProxy(advisedSupport).getProxy();//调用JdkDynamicAopProxy创建一个jdk的
            }
        }
        return bean;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;

    }
}
