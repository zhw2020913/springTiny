package java.us.codecraft.tinyioc.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 15:32
 */
//代理工厂
public class ProxyFactory extends AdvisedSupport implements AopProxy{
    protected final AopProxy createAopProxy() {
        //Cglib2AopProxy extends AbstractAopProxy
        //AbstractAopProxy implements AopProxy
        return new Cglib2AopProxy(this);
    }
    //通过代理类对象实现获得代理的方法是不是有问题？
    //属于子实现父调用的范畴
    @Override
    public Object getProxy() {
         return createAopProxy().getProxy();
    }
}
