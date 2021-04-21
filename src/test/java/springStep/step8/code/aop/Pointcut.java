package springStep.step8.code.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 21:11
 */
//构成一个切面接口
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
