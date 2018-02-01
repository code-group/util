package exception;

/**
 * @author zhull
 * @date 2018/1/30
 * <P>description: 基础异常类</P>
 */
public class BaseException extends Exception {

    private int errorCode;

    public BaseException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public BaseException(int errorCode, String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
