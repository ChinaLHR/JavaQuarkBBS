package com.quark.common.exception;

/**
 * @Author LHR
 * Create By 2017/8/26
 */
public class ServiceProcessException extends RuntimeException {

    public ServiceProcessException(String message) {
        super(message);
    }

    public ServiceProcessException(String message, Throwable cause) {
        super(message, cause);
    }


}
