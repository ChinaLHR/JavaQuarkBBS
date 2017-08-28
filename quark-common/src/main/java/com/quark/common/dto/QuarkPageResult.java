package com.quark.common.dto;

import com.quark.common.enums.StateEnum;

import java.io.Serializable;

/**
 * @Author LHR
 * Create By 2017/8/28
 */
public class QuarkPageResult implements Serializable {
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

    /**
     * 返回总页数
     */
    private long pageSize;

    /**
     * 本页返回数量
     */
    private Integer total;

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

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public QuarkPageResult(Integer status, Object data, long pageSize,Integer total) {
        this.status = status;
        this.data = data;
        this.pageSize = pageSize;
        this.total = total;
    }

    public QuarkPageResult(Integer status, String error) {
        this.status = status;
        this.error = error;
    }

    public static QuarkPageResult ok(Object data,long pageSize,Integer total){
        return new QuarkPageResult(StateEnum.SUCCESS.getState(),data,pageSize,total);
    }

    public static QuarkPageResult error(String error){
        return new QuarkPageResult(StateEnum.ERROR.getState(),error);
    }

}
