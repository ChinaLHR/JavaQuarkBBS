package com.quark.admin.enums;

/**
 * @Author LHR
 * Create By 2017/8/11
 */
public enum  SqlErrorEnum {
    RUNTIMEERROR("数据库执行出现异常"),REPEATCOLUMN("重复字段异常");

    private String ErrorMessage;

    SqlErrorEnum(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }
}
