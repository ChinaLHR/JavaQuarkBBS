package com.quark.admin.exception;

/**
 * 数据库执行异常
 * @Author LHR
 * Create By 2017/8/11
 */
public class SqlRuntimeExecption extends RuntimeException {
    public SqlRuntimeExecption(String message) {
        super(message);
    }

    public SqlRuntimeExecption(String message, Throwable cause) {
        super(message, cause);
    }
}
