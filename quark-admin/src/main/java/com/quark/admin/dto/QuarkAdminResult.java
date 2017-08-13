package com.quark.admin.dto;

import com.quark.admin.enums.StateEnum;

import java.io.Serializable;

/**
 * 后台操作响应数据
 *
 * @Author LHR
 * Create By 2017/8/11
 */
public class QuarkAdminResult implements Serializable {

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

    /**
     * 请求成功携带数据
     * @param status
     */
    public QuarkAdminResult(Integer status) {
        this.status = status;
    }

    /**
     * 请求成功并携带
     * @param status
     * @param data
     */
    public QuarkAdminResult(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    /**
     * 请求失败
     * @param status
     * @param error
     */
    public QuarkAdminResult(Integer status, String error) {
        this.status = status;
        this.error = error;
    }

    public static QuarkAdminResult ok(){
        return new QuarkAdminResult(StateEnum.SUCCESS.getState());
    }

    public static QuarkAdminResult ok(Object data){
        return new QuarkAdminResult(StateEnum.SUCCESS.getState(),data);
    }

    public static QuarkAdminResult error(String error){
        return new QuarkAdminResult(StateEnum.ERROR.getState(),error);
    }


}
