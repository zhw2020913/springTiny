package springStep.step10.test.aop;

import org.junit.Test;
import springStep.step10.code.aop.AdvisedSupport;
import springStep.step10.code.aop.JdkDynamicAopProxy;
import springStep.step10.code.aop.TargetSource;
import springStep.step10.code.context.ApplicationContext;
import springStep.step10.code.context.ClassPathXmlApplicationContext;
import springStep.step10.test.HelloWorldService;
import springStep.step10.test.HelloWorldServiceImpl;


/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 16:46
 */
public class JdkDynamicAopProxyTest {

    @Test
    public void interceptorTest() throws Exception {
        // --------- 未使用aop
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc10.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
        //用于说明一个问题，提高程序执行的效率
        //使用aop后
        //1.设置被代理的对象(Joinpoint)就是代理的切入点，那class 中的指定方法
        AdvisedSupport advisedSupport = new AdvisedSupport();

        //TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldService.class);
        //第十步发生改变
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class,
                HelloWorldService.class);
        advisedSupport.setTargetSource(targetSource);
        //2.设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();//设置已经创建的Timer拦截器
        advisedSupport.setMethodInterceptor(timerInterceptor);
        //3.创建代理(Proxy)
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();
        //4.基于AOP的调用
        helloWorldServiceProxy.helloWorld();



    }
}
