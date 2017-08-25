package com.quark.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quark.common.utils.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author LHR
 * Create By 2017/8/18
 *
 * 回复
 */
@Entity
@Table(name = "quark_reply")
public class Reply implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //回复的内容
    @Column(columnDefinition = "text", nullable = false)
    private String content;

    //回复时间
    @JsonFormat(pattern = Constants.DATETIME_FORMAT)
    private Date initTime;

    //点赞个数
    @Column(nullable = false)
    private Integer up;

    //与话题的关联关系
    @ManyToOne
    @JoinColumn(nullable = false, name = "posts_id")
    private Posts posts;

    //与用户的关联关系
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
