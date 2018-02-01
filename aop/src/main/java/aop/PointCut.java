package aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zhull
 * @date 2018/1/31
 * <P>description: 切点，可写在拦截器内，更直观</P>
 */
@Component
public class PointCut {

    /**
     * 定义拦截规则 <br/>
     * 执行aop包内所有public方法，除了带有注解@CheckToken.IgnoreMethodToken
     */
    @Pointcut(
            "(execution(!@aop.CheckToken.IgnoreMethodToken public * aop..*.*(..)))" +
                    " && " +
                    "(execution(public * aop..*.*(..)))"
//            "within(aop.*)"
    )
    public void pointCut() {
    }

}
