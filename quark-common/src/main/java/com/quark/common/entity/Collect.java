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
 * 收藏
 */
@Entity
@Table(name = "quark_collect")
public class Collect implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;

    //与帖子的关联关系：立即加载
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "posts_id")
    private Posts posts;

    //与用户的关联关系：立即加载
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    //收藏时间
    @JsonFormat(pattern = Constants.DATETIME_FORMAT)
    private Date initTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }
}
