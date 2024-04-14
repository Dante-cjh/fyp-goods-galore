package com.macro.mall.common.api;

/**
 * API返回码接口
 * Created by Jiahan Chen
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}
