package zhan.wang.moon.datamodel;

import lombok.Getter;

/**
 * 
 * 基础API返回类，包含状态码、状态码信息、返回业务结果对象
 *
 */
@Getter
public class BaseResponse {

    /**
     * 状态码
     */
    protected String code;
    /**
     * 状态码信息
     */
    protected String message;
    /**
     * 返回业务结果
     */
    protected Object data;
    
    /**
     * 构建一个不带业务结果的返回结果对象
     * @param code      状态码
     * @param message   状态码信息
     */
    public BaseResponse(String code, String message) {
        this(code, message, "");
    }

    /**
     * 构建一个标准的返回结果对象
     * @param code      状态码
     * @param message   状态码信息
     * @param data      业务结果
     */
    public BaseResponse(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
