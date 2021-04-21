package springStep.step10.code.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/19 15:36
 */
//将代理的目标和class相结合组合成一个实体
public class TargetSource {
    private Class<?> targetClass;//目标类

    private Object target;//实体
    //第10步增加
    private Class<?>[] interfaces;//所实现的接口

    public TargetSource(Object target, Class<?> targetClass, Class<?>... interfaces) {
        this.target = target;
        this.targetClass = targetClass;
        this.interfaces = interfaces;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }
    public Object getTarget() {
        return target;
    }
    public Class<?>[] getInterfaces() {
        return interfaces;
    }

}
