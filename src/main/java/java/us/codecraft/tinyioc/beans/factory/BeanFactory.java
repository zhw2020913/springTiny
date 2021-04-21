package java.us.codecraft.tinyioc.beans.factory;

import java.us.codecraft.tinyioc.beans.BeanDefinition;


/**
 * @author yihua.huang@dianping.com
 */
public interface BeanFactory {//通过map c存储bean
     Object getBean(String name) throws Exception;

}
