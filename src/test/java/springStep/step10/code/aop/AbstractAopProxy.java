package springStep.step10.code.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 15:42
 */
public abstract class AbstractAopProxy implements AopProxy{//将代理和代理需要的数据对象合并
    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
