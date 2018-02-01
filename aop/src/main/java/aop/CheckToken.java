package aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhull
 * @date 2018/2/1
 * <P>description: </P>
 */
public class CheckToken {

    /**
     * 忽略方法
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface IgnoreMethodToken {

    }

    /**
     * 忽略参数
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface IgnoreParamToken {

    }
}
