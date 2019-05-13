package aop;

import exception.ParameterException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import validator.ValidateUtil;

import java.lang.reflect.Method;

/**
 * @author zhull
 * @date 2018/1/31
 * <P>description: AOP配置类</P>
 */
@Slf4j
@Aspect
@Component
public class Interceptor {

    @Around("aop.PointCut.pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        final MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 被拦截的方法
        final Method method = methodSignature.getMethod();
        // 被拦截方法的方法名
        final String methodName = method.getName();
        // 被拦截方法的参数
        final Object[] args = pjp.getArgs();

        try {
            // 被拦截方法的描述
            String methodDescription = getAopMethodDescription(pjp);
            // 验参
            checkParam(args);

            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("[Aspect1] around advise 2");
        return null;
    }

    @Before(value = "aop.PointCut.pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("[Aspect2] before advise");
    }

    @AfterReturning(value = "aop.PointCut.pointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("[Aspect3] afterReturning advise");
    }

    @AfterThrowing(value = "aop.PointCut.pointCut()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("[Aspect4] afterThrowing advise");
    }

    @After(value = "aop.PointCut.pointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("[Aspect5] after advise");
    }

    /**
     * <P>date:        2018/2/1</P>
     * <P>description: 参数校验</P>
     *
     * @param args 参数
     */
    private void checkParam(Object... args) throws ParameterException {
        for (Object arg : args) {
            if (arg.getClass().getAnnotation(CheckToken.IgnoreParamToken.class) != null) {
                ValidateUtil.validate(arg);
            }
        }
    }

    /**
     * <P>date: 2019/5/13
     * 获取方法签名中的方法名
     **/
    private String getAopMethodDescription(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method declaredMethod = pjp.getTarget().getClass()
                .getDeclaredMethod(methodSignature.getName(), methodSignature.getMethod().getParameterTypes());
        if (declaredMethod.isAnnotationPresent(MethodDescription.class)) {
            return declaredMethod.getAnnotation(MethodDescription.class).value();
        }
        return "";
    }
}
