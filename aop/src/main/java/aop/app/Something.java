package aop.app;

import aop.CheckToken;
import aop.MethodDescription;
import org.springframework.stereotype.Component;

/**
 * @author zhull
 * @date 2018/1/31
 * <P>description: 使用AOP</P>
 * @see aop.Interceptor
 * @see aop.PointCut
 */
@Component
public class Something {


    public void enableAop(@CheckToken.IgnoreParamToken String name) {
        System.out.println("enableAop");
    }

    /**
     * 忽略AOP
     */
    @CheckToken.IgnoreMethodToken
    public void unableAop() {
        System.out.println("unableAop");
    }

    /**
     * 注：如果该类有接口，将注解标在实现方法上
     */
    @MethodDescription("一个有描述的方法")
    public void methodWithDescription() {
        System.out.println("I have a method description");
    }
}
