package springStep.step8.test;



/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/20 8:43
 */
public class BeanInitializeLogger implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        System.out.println("初始化 bean " + beanName + " 开始!");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        System.out.println("初始化 bean " + beanName + " 结束!");
        return bean;
    }
}
