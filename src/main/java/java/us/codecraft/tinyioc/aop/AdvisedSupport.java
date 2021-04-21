package java.us.codecraft.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/12 15:59
 */
//代理相关的元数据
public class AdvisedSupport {
    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;//仅仅只是一个接口类
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
