package java.us.codecraft.tinyioc.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 9:47
 *
 */
//切入点通知
public interface PointcutAdvisor extends Advisor {
    Pointcut getPointcut();
}
