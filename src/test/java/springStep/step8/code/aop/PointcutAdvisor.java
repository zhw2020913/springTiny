package springStep.step8.code.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 21:11
 */
//将切面和通知结合
public interface PointcutAdvisor extends Advisor{
    Pointcut getPointcut();
}
