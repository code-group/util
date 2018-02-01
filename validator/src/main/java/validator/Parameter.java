package validator;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author zhull
 * @date 2018/1/31
 * <P>description: 参数类</P>
 * @see ValidateUtil
 */
@Data
public class Parameter {

    /**
     * 姓名
     * 默认group为Default.class
     */
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     * 如去掉@NotNull，字段可为空
     */
    @Range(min = 0, max = 200)
    @NotNull
    private Integer age;

    /**
     * 房产证号
     */
    @NotNull(groups = HouseChecks.class)
    private String houseNo;

}
