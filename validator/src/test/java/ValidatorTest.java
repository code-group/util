import exception.ParameterException;
import org.junit.Test;
import validator.HouseChecks;
import validator.Parameter;
import validator.ValidateUtil;

import javax.validation.groups.Default;

/**
 * @author zhull
 * @date 2018/1/31
 * <P>description: 参数、工具调用类</P>
 */
public class ValidatorTest {

    @Test
    public void testCheck() {
        Parameter parameter = new Parameter();
        try {
            ValidateUtil.validate(parameter);
        } catch (ParameterException e) {
            System.out.println("1. " + e.getMessage());
            e.printStackTrace();
        }

        parameter.setName("Alice");
        parameter.setAge(-1);
        try {
            ValidateUtil.validate(parameter, HouseChecks.class);
            ValidateUtil.validateAll(parameter, Default.class, HouseChecks.class);
        } catch (ParameterException e) {
            System.out.println("2. " + e.getMessage());
            e.printStackTrace();
        }

    }
}
