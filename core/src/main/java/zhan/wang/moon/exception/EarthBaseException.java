package zhan.wang.moon.exception;

import lombok.Getter;

@Getter
public class EarthBaseException extends RuntimeException{

    private static final String DEFAULT_FAIL = StatusCode.FAIL.getCode();

    // 状态码
    protected final String code;
    // 提示信息
    protected final String message;
    // 具体异常
    protected final Throwable cause;

    /**
     * 构造函数
     *
     * @param code      状态码
     * @param message   状态信息
     * @param cause     具体错误
     */
    public EarthBaseException(String code, String message, Throwable cause) {
        this.code = code;
        this.message = message;
        this.cause = cause;
    }

    /**
     * 构造函数
     * @param statusCode Muse 状态枚举
     * @param cause 具体错误
     */
    public EarthBaseException(StatusCode statusCode, Throwable cause) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.cause = cause;
    }

    /**
     * 构造函数
     * @param statusCode Muse 状态枚举
     * {@link StatusCode}
     */
    public EarthBaseException(StatusCode statusCode) {
        this(statusCode, null);
    }

    /**
     * 构造函数
     * @param message   状态信息
     */
    public EarthBaseException(String message) {
        this(DEFAULT_FAIL, message, null);
    }

    /**
     * 构造函数
     * @param e 外部异常
     */
    public EarthBaseException(Throwable e) {
        this(DEFAULT_FAIL, e.getMessage(), e);
    }

    /**
     * 构造函数
     * @param message   状态信息
     * @param e         外部异常
     */
    public EarthBaseException(String message, Exception e) {
        this(DEFAULT_FAIL, message, e);
    }


}
