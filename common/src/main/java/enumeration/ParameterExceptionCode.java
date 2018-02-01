package enumeration;

/**
 * @author zhull
 * @date 2018/1/30
 * <P>description: </P>
 */
public enum ParameterExceptionCode {
    PARAMETER_ERROR(8000, "参数异常");

    public int code;
    public String desc;

    ParameterExceptionCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
