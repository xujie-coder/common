package com.xjtu.base.exception;

/**
 * 错误信息
 *
 * @author xujie
 * @since 2025/01/07 15:35
 */
public interface ErrorCode {
    /**
     * 错误码
     *
     * @return 错误码
     */
    String getCode();

    /**
     *错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
