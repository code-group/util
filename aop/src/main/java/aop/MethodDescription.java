package aop;

import java.lang.annotation.*;

/**
 * @author zhull
 * @date 2019/5/13
 * <P>方法描述</P>
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodDescription {
    String value();
}
