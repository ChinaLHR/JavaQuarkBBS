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
 * 帖子
 */
@Entity
@Table(name = "quark_posts")
public class Posts implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //标签Id
    @Column(name = "label_id")
    private Integer labelId;

    //标题
    @Column(unique = true, nullable = false)
    private String title;

    //内容
    @Column(columnDefinition = "text")
    private String content;

    //发布时间
    @Column(nullable = false)
    @JsonFormat(pattern = Constants.DATETIME_FORMAT)
    private Date initTime;

    //是否置顶
    private boolean top;

    //是否精华
    private boolean good;

    //与用户的关联关系
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    @JsonIgnore
    private User user;


    //回复数量
    @Column(name = "reply_count")
    private int replyCount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

}
