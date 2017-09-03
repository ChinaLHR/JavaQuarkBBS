package com.quark.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quark.common.utils.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author LHR
 * Create By 2017/8/18
 *
 * 用户
 */
@Entity
@Table(name="quark_user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //注册邮箱
    @Column(nullable = false)
    private String email;

    // 用户名
    @Column(unique = true, nullable = false)
    private String username;

    // 密码
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    // 头像
    private String icon ="http://127.0.0.1/images/upload/default.png";

    // 个人签名
    private String signature;

    // 注册时间
    @Column(nullable = false)
    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone = "GMT+8")
    private Date initTime;

    //性别 0 ：男 1：女
    private Integer sex = 0;

    //是否被封禁,默认为１：开启
    @Column(nullable = false)
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
                ", password='" + password + '\'' +
                ", Icon='" + icon + '\'' +
                ", signature='" + signature + '\'' +
                ", initTime=" + initTime +
                ", sex=" + sex +
                ", enable=" + enable +
                '}';
    }
}
