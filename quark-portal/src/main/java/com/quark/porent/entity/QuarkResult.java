package com.quark.porent.entity;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * Rest响应数据
 *
 * @Author LHR
 * Create By 2017/8/11
 */
public class QuarkResult implements Serializable {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 错误信息
     */
    private String error;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public QuarkResult(Integer status, Object data, String error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }
}
