package java.us.codecraft.tinyioc.aop;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 15:49
 */
//将放置代理数据的AdvisedSupport 和 AopProxy进行组装
public abstract class  AbstractAopProxy implements AopProxy{

    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
