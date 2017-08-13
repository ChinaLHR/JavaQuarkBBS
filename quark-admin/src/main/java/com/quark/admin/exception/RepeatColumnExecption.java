package com.quark.admin.exception;

/**
 * 重复字段异常
 *
 * @Author LHR
 * Create By 2017/8/13
 */
public class RepeatColumnExecption extends RuntimeException{

    public RepeatColumnExecption() {
    }

    public RepeatColumnExecption(String message) {
        super(message);
    }
}
