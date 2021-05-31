package zhan.wang.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum StatusCode {
    SUCCESS("0000","成功"),
    MISS_PARAMETER("0001", "参数缺失"),
    NULL_PARAMETER_VAL("0002", "参数值不能为空"),


    DB_ERR("0100", "数据库返回异常"),

    FAIL("0500","失败"),
    ENCODE_ERR("1000", "编码错误");


    private String code;
    private String message;

}
