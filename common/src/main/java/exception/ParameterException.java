package exception;

/**
 * @author zhull
 * @date 2018/1/30
 * <P>description: 参数异常</P>
 */
public class ParameterException extends BaseException {

    public ParameterException(int errorCode) {
        super(errorCode);
    }

    public ParameterException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public ParameterException(int errorCode, String errorMessage, Throwable e) {
        super(errorCode, errorMessage, e);
    }
}
