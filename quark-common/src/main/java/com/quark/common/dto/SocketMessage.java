package com.quark.common.dto;

import java.io.Serializable;

/**
 * WebSocket通知消息类
 *
 * @Author LHR
 * Create By 2017/9/6
 */
public class SocketMessage implements Serializable{

    private Integer notice;

    private String message;

    public Integer getNotice() {
        return notice;
    }

    public void setNotice(Integer notice) {
        this.notice = notice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SocketMessage(Integer notice, String message) {
        this.notice = notice;
        this.message = message;
    }

    public SocketMessage(Integer notice) {
        this.notice = notice;
    }

    public static SocketMessage build(Integer notice){
        return new SocketMessage(notice);
    }

    public static SocketMessage build(Integer notice,String message){
        return new SocketMessage(notice,message);
    }
}
