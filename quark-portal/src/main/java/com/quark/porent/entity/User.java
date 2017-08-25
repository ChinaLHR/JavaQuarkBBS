package com.quark.porent.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author LHR
 * Create By 2017/8/18
 * <p>
 * 用户
 */
public class User implements Serializable {

    private Integer id;


    private String email;


    private String username;


    // 头像
    private String Icon;

    // 个人签名
    private String signature;


    private Date initTime;

    //性别 0 ：男 1：女
    private Integer sex = 0;

    //是否被封禁,默认为１：开启
    private Integer enable = 1;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", Icon='" + Icon + '\'' +
                ", signature='" + signature + '\'' +
                ", initTime=" + initTime +
                ", sex=" + sex +
                ", enable=" + enable +
                '}';
    }
}
