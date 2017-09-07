package com.quark.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quark.common.utils.Constants;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author LHR
 * Create By 2017/8/18
 *
 * 通知
 */
@Entity
@Table(name = "quark_notification")
public class Notification {

    @Id
    @GeneratedValue
    private Integer id;

    //通知是否已读
    @Column(name = "is_read")
    private boolean isRead = false;

    //要通知的用户：立即加载
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "touser_id")
    private User touser;

    //发起通知的用户
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "fromuser_id")
    private User fromuser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "posts_id")
    private Posts posts;

    //发布时间
    @Column(nullable = false)
    @JsonFormat(pattern = Constants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date initTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public User getTouser() {
        return touser;
    }

    public void setTouser(User touser) {
        this.touser = touser;
    }

    public User getFromuser() {
        return fromuser;
    }

    public void setFromuser(User fromuser) {
        this.fromuser = fromuser;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }
}
