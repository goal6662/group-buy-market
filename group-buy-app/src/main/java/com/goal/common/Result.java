package com.goal.common;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Result<T> {

    /**
     * 状态码
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(ResponseCode.OPERATE_SUCCESS.getCode(), ResponseCode.OPERATE_SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResponseCode.OPERATE_SUCCESS.getCode(), ResponseCode.OPERATE_SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResponseCode.OPERATE_FAIL.getCode(), ResponseCode.OPERATE_FAIL.getMessage(), null);
    }

    public static <T> Result<T> fail(ResponseCode code) {
        return new Result<>(code.getCode(), code.getMessage(), null);
    }

    public static <T> Result<T> fail(ResponseCode code, String message) {
        return new Result<>(code.getCode(), StringUtils.isBlank(message) ? code.getMessage() : message, null);
    }

    private Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}