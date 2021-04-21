package java.us.codecraft.tinyioc.aop;

import org.aopalliance.aop.Advice;

/**
 * @author zhw
 * @version 1.0
 * @date 2021/4/13 9:14
 */
//获取通知的接口
public interface Advisor {
    Advice getAdvice();
}
