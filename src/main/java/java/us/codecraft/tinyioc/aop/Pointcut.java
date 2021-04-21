package java.us.codecraft.tinyioc.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 9:34
 */
//目的就是获得切入点，就是那个类中的哪个方法
public interface Pointcut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
