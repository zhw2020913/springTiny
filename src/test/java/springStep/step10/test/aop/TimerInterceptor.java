package springStep.step10.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class TimerInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long time = System.nanoTime();
		System.out.println("调用方法 " + invocation.getMethod().getName() + " 开始!");
		Object proceed = invocation.proceed();
		System.out.println("调用方法 " + invocation.getMethod().getName() + " 结束! 一共耗时 " + (System.nanoTime() - time)
				+ " 纳秒.");
		return proceed;
	}

}
