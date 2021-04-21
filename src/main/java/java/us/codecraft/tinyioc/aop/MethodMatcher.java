package java.us.codecraft.tinyioc.aop;

import java.lang.reflect.Method;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 9:35
 */
//方法匹配获得指定类中的指定方法
public interface MethodMatcher {
    boolean matches(Method method, Class targetClass);
}
