package springStep.step10.code.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 21:11
 */
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
