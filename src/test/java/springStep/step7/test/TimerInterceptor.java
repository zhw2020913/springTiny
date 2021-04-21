package springStep.step7.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 16:47
 */
public class TimerInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        long time = System.nanoTime();
        System.out.println("调用方法 " + invocation.getMethod().getName() + " 开始!");
        Object proceed = invocation.proceed();//转到下一个拦截器
        System.out.println("**********转到下一个拦截器***********");
        System.out.println("调用方法 " + invocation.getMethod().getName() + " 结束! 花费 " + (System.nanoTime() - time)
                + " 纳秒.");
        return proceed;
    }
}
