package com.self.base.system.base;

/**
 * API 错误类
 */
public class ApiException extends Exception{

    private Integer code;   // 错误码

    /**
     * 构造函数
     * @param code      错误码
     * @param message   错误信息
     */
    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 获取错误码
     * @return 错误码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置错误码
     * @param code 错误码
     */
    public void setCode(Integer code) {
        this.code = code;
    }
}
