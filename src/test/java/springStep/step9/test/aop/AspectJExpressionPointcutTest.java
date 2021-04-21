package springStep.step9.test.aop;

import org.junit.Assert;
import org.junit.Test;
import springStep.step9.code.aop.AspectJExpressionPointcut;
import springStep.step9.test.HelloWorldServiceImpl;


/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 8:52
 */
public class AspectJExpressionPointcutTest {
    @Test
    public void testClassFilter(){//测试类的过滤，能不能匹配到指定类
        String expression = "execution(* springStep.step9.test.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);

        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
    }
    @Test
    public void testMethodFilter() throws NoSuchMethodException {
        String expression = "execution(* springStep.step9.test.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloWorldServiceImpl.class.getDeclaredMethod("helloWorld"),HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
    }
}
