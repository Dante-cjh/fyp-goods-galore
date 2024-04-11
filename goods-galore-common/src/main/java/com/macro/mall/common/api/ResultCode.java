package com.macro.mall.common.api;

/**
 * API返回码封装类
 * Created by macro on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "Successful operation"),
    FAILED(500, "failure of operation"),
    VALIDATE_FAILED(404, "Parameter Check Failure"),
    UNAUTHORIZED(401, "Not logged in or Token has expired"),
    FORBIDDEN(403, "No relevant permissions");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
