package springStep.step10.code.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 15:38
 */

public class ReflectiveMethodInvocation implements MethodInvocation {
    protected Object target;//目标对象

    protected   Method method;//方法

    protected Object[] arguments;//参数数组

    public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.arguments = args;
    }
    /**
     * 返回正在被调用得方法~~~  返回的是当前Method对象。
     * 此时，效果同父类的AccessibleObject getStaticPart() 这个方法
     */
    @Override
    public Method getMethod() {
        return method;
    }
    /**
     * 将参数作为数组对象获取，可以更改此数组中的元素值以更改参数。
     * 通常用来获取调用目标方法的参数
     */
    @Override
    public Object[] getArguments() {
        return arguments;
    }
    /**
     * 转到拦截器链中的下一个拦截器
     */
    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target,arguments);
    }
    /**
     * 返回保存当前连接点静态部分【的对象】，这里一般指被代理的目标对象
     */
    @Override
    public Object getThis() {
        return target;
    }
    /**
     * 返回此静态连接点  一般就为当前的Method(至少目前的唯一实现是MethodInvocation,所以连接点得静态部分肯定就是本方法)
     */
    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
