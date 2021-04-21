package springStep.step10.code.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 15:51
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy{

    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }
    protected final AopProxy createAopProxy() {
        return new Cglib2AopProxy(this);
    }
}
