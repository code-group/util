package aop.app;

import aop.CheckToken;
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

}
