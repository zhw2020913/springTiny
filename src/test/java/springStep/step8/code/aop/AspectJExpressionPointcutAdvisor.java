package springStep.step8.code.aop;

import org.aopalliance.aop.Advice;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 21:16
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor{
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    private Advice advice;
    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
