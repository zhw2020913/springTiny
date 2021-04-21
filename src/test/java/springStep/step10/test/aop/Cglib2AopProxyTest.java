package springStep.step10.test.aop;

import org.junit.Test;
import springStep.step10.code.aop.AdvisedSupport;
import springStep.step10.code.aop.Cglib2AopProxy;
import springStep.step10.code.aop.TargetSource;
import springStep.step10.code.context.ApplicationContext;
import springStep.step10.code.context.ClassPathXmlApplicationContext;
import springStep.step10.test.HelloWorldService;
import springStep.step10.test.HelloWorldServiceImpl;


/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 17:49
 */
public class Cglib2AopProxyTest {
    @Test
    public void testInterceptor() throws Exception {
        //未使用aop
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc10.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
        //使用aop
        // 1. 设置被代理对象(Joinpoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class,HelloWorldService.class);
        advisedSupport.setTargetSource(targetSource);
        //2.设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);
        //3.创建代理(Proxy)
        Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) cglib2AopProxy.getProxy();
        //4.基于AOP的调用
        helloWorldServiceProxy.helloWorld();

    }
}
