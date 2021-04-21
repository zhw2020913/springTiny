package java.us.codecraft.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.us.codecraft.tinyioc.beans.BeanPostProcessor;
import java.us.codecraft.tinyioc.beans.factory.AbstractBeanFactory;
import java.us.codecraft.tinyioc.beans.factory.BeanFactory;
import java.util.List;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 11:15
 */
//自动创建生成代理
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware{
    private AbstractBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory
                .getBeansForType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
               // AdvisedSupport advisedSupport = new AdvisedSupport();
                ProxyFactory advisedSupport = new ProxyFactory();
                //这个地方改动是不是意味着生成的aop代理仅仅只是Cglib生成的静态代理
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                //这个地方依然属于反射范畴 getInterfaces() 用于获得这个对象所实现的接口
                //构造方法源代码有问题，主要因为这个地方getInterfaces()返回的是一个数组对象
                TargetSource targetSource = new TargetSource(bean,bean.getClass() ,bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);
                //return new JdkDynamicAopProxy(advisedSupport).getProxy();
                return advisedSupport.getProxy();

            }
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }
}
