package springStep.step9.code.aop;

import org.aopalliance.aop.Advice;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 21:09
 */
public interface Advisor {
    Advice getAdvice();
}
