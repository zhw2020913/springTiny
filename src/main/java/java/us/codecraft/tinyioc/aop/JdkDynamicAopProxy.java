package java.us.codecraft.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/12 16:02
 */
//jdk的aop动态代理
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //要实现其接口
        MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
        if (advised.getMethodMatcher() != null
                && advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(),
                    method, args));
        } else {
            return method.invoke(advised.getTargetSource().getTarget(), args);
        }
    }

    @Override
    public Object getProxy() {
        //返回一个代理
        return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { advised.getTargetSource()
                .getTargetClass() }, this);
    }
}
