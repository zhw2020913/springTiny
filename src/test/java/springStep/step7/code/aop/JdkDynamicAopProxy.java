package springStep.step7.code.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 15:39
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        System.out.println("**********进入JdkDynamicAopProxy有参构造函数，设置属性AdvisedSupport ****");
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        //用于创建代理类的实例
        //getClass()：取得当前对象所属的Class对象
        //getClassLoader()：取得该Class对象的类装载器 这两个方法与java 中的jvm 有关
        System.out.println("************Jdk动态代理中 创建一个aop代理实例");
        return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { advised.getTargetSource()
                .getTargetClass() }, this);
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        System.out.println("*************执行Jdk动态代理中的invoke方法*******");
        MethodInterceptor methodInterceptor = advised.getMethodInterceptor();//从
        System.out.println("***********从AdvisedSupport 中获得定义的拦截器");
        //其中advised.getTargetSource().getTarget() 为对应的实例类对象
        return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method,
                args));//子重父用
    }
}
