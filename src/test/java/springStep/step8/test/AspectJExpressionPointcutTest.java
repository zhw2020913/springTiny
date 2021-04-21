package springStep.step8.test;

import org.junit.Assert;
import org.junit.Test;
import springStep.step8.code.aop.AspectJExpressionPointcut;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 8:52
 */
public class AspectJExpressionPointcutTest {
    @Test
    public void testClassFilter(){//测试类的过滤，能不能匹配到指定类
        //指定切入点的表达式
        String expression = "execution(* springStep.step8.test.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        //HelloWorldServiceImpl.class 为Class targetClass
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
    }
    @Test
    public void testMethodFilter() throws NoSuchMethodException {
        String expression = "execution(* springStep.step8.test.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        //限定为某类中的方法
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloWorldServiceImpl.class.getDeclaredMethod("helloWorld"),HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
    }
}
