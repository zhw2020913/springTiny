package springStep.step8.code.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 21:10
 */
public interface ClassFilter {
    boolean matches(Class targetClass);
}
