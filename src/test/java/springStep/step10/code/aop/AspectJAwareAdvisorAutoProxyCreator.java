package springStep.step10.code.aop;

import org.aopalliance.intercept.MethodInterceptor;
import springStep.step10.code.beans.BeanPostProcessor;
import springStep.step10.code.beans.factory.AbstractBeanFactory;
import springStep.step10.code.beans.factory.BeanFactory;

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

        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
                //AdvisedSupport advisedSupport = new AdvisedSupport();
                //第十步进行更改
                ProxyFactory advisedSupport = new ProxyFactory();
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                //TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces());
                //第十步更改为
                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);
                //第十步更改为
                //return new JdkDynamicAopProxy(advisedSupport).getProxy();
               // System.out.println("直接用Cglib2AopProxy生成aop代理");
                return advisedSupport.getProxy();//直接用Cglib2AopProxy生成aop代理
            }
        }
        return bean;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;

    }
}
