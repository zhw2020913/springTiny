package java.us.codecraft.tinyioc.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 9:16
 */
//类过滤，获得切入点的类
public interface ClassFilter {
    boolean matches(Class targetClass);
}
