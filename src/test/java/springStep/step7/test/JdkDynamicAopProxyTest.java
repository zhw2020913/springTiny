package springStep.step7.test;

import org.junit.Test;
import springStep.step7.code.aop.AdvisedSupport;
import springStep.step7.code.aop.JdkDynamicAopProxy;
import springStep.step7.code.aop.TargetSource;
import springStep.step7.code.context.ApplicationContext;
import springStep.step7.code.context.ClassPathXmlApplicationContext;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 16:46
 */
public class JdkDynamicAopProxyTest {

    @Test
    public void interceptorTest() throws Exception {
        // --------- 未使用aop
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc7.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
        //使用aop后
        //1.设置被代理的对象(Joinpoint)就是代理的切入点，那class 中的指定方法
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService,HelloWorldService.class);
        //其中第一个参数helloWorldService 为一个实体类，第二个参数HelloWorldService.class 为其对应生成的类路径
        advisedSupport.setTargetSource(targetSource);

        //2.设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();//设置已经创建的Timer拦截器
        advisedSupport.setMethodInterceptor(timerInterceptor);
        //3.创建代理(Proxy)
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService helloWorldService1 = (HelloWorldService) jdkDynamicAopProxy.getProxy();
        //4.基于AOP的调用
        helloWorldService1.helloWorld();



    }
}
