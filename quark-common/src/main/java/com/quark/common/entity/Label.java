package com.quark.common.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author LHR
 * Create By 2017/8/18
 *
 * 标签
 */
@Entity
@Table(name = "quark_label")
public class Label implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //标签名称
    @Column(nullable = false, unique = true)
    private String name;

    //主题数量
    @Column(name = "posts_count")
    private Integer postsCount = 0;

    //详情
    private String details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
