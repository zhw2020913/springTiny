package springStep.step9.code.aop;

import springStep.step9.code.beans.factory.BeanFactory;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 10:02
 */
public interface BeanFactoryAware {//第九步增加
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
