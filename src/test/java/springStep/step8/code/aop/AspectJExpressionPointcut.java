package springStep.step8.code.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 21:16
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher{
    private PointcutParser pointcutParser;//切入点解析
    private String expression;//表达式
    private PointcutExpression pointcutExpression;//切入点表达式
    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();
    static {//设置切入点优先执行的优先级
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }
    public AspectJExpressionPointcut() {
        //无参构造方法调用类本身有参构造方法
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    public AspectJExpressionPointcut(Set<PointcutPrimitive> supportedPrimitives) {
        //
        pointcutParser = PointcutParser
                .getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
        //根据默认支持的优先级获得pointcutParser,设置其线程的优先级
    }
    protected void checkReadyToMatch(){
        System.out.println("判断pointcutExpression是否为空，为空则通过pointcutParser解析expression创建");
        if (pointcutExpression == null){
            pointcutExpression = buildPointcutExpression();
            System.out.println("pointcutExpression 为:"+pointcutExpression);
        }
    }
    private PointcutExpression buildPointcutExpression() {
        //通过解析表达式生成pointcutExpression对象其getPointcutExpression()为expression
        return pointcutParser.parsePointcutExpression(expression);
    }
    public void setExpression(String expression) {
        this.expression = expression;
    }
    @Override
    public boolean matches(Class targetClass) {
        //实现接口ClassFilter 中的match方法
        System.out.println("调用AspectJExpressionPointcut 匹配类的方法");
        checkReadyToMatch();//判断pointcutExpression是否为空并进行创建
        //判断是否与切入点的匹配
        System.out.println("进行匹配类其中targetClass为:"+targetClass);
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    @Override
    public boolean matches(Method method, Class targetClass) {
        //第二个参数未使用
        checkReadyToMatch();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        System.out.println();
        if (shadowMatch.alwaysMatches()) {
            return true;
        } else if (shadowMatch.neverMatches()) {
            return false;
        }
        // TODO:其他情况不判断了！见org.springframework.aop.aspectj.RuntimeTestWalker
        return false;

    }

    @Override
    public ClassFilter getClassFilter() {
        //用于获得ClassFilter中匹配类的match方法
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        //用于获得MethodMatcher中匹配类中方法的match方法
        return this;
    }
}
