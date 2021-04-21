package springStep.step8.code.aop;

import java.lang.reflect.Method;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 21:10
 */
public interface MethodMatcher {
    boolean matches(Method method, Class targetClass);
}
