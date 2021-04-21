package springStep.step8.code.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 15:36
 */
//将代理的目标和class相结合组合成一个实体
public class TargetSource {
    private Class targetClass;

    private Object target;

    public TargetSource(Object target, Class<?> targetClass) {
        this.target = target;
        this.targetClass = targetClass;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }
}
