package validator;

import enumeration.ParameterExceptionCode;
import exception.ParameterException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @author zhull
 * @date 2018/1/29
 * <P>description: 参数校验工具</P>
 */
@Slf4j
public class ValidateUtil {

    private static Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * <P>date:        2018/1/30</P>
     * <P>description: 验证参数</P>
     * <T>存在的作用，是为了参数中能够出现T这种数据类型
     *
     * @param object 对象
     * @param groups 分组
     * @param <T>    范型
     * @throws ParameterException 参数异常
     */
    public static <T> void validate(T object, Class<?>... groups) throws ParameterException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
        for (ConstraintViolation constraintViolation : constraintViolations) {
            throw new ParameterException(ParameterExceptionCode.PARAMETER_ERROR.code,
                    constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage());
        }
    }

    /**
     * <P>date:        2018/1/31</P>
     * <P>description: 验证所有参数</P>
     *
     * @param object 对象
     * @param groups 分组
     * @param <T>    范型
     * @throws ParameterException 参数异常
     */
    public static <T> void validateAll(T object, Class<?>... groups) throws ParameterException {
        Set<ConstraintViolation<T>> contraintViolations = validator.validate(object, groups);
        if (contraintViolations.size() > 0) {
            List<String> messages = new ArrayList<>();
            for (ConstraintViolation violation : contraintViolations) {
                messages.add(violation.getPropertyPath() + " " + violation.getMessage());
            }
            throw new ParameterException(ParameterExceptionCode.PARAMETER_ERROR.code, messages.toString());
        }
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        try {
            validate(list);
        } catch (ParameterException e) {
            e.printStackTrace();
        }
    }
}
