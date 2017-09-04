package com.quark.rest.service;

import com.quark.common.base.BaseService;
import com.quark.common.entity.Label;
import com.quark.common.entity.Posts;
import com.quark.common.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author LHR
 * Create By 2017/8/26
 */
public interface PostsService extends BaseService<Posts> {

    /**
     * 保存帖子
     * @param posts 帖子
     * @param labelId 标签id
     */
    void savePosts(Posts posts,Integer labelId,User user);
    /**
     * 翻页查询帖子
     * @param type
     * @param search
     * @param pageNo
     * @param length
     * @return
     */
    Page<Posts> getPostsByPage(String type ,String search, int pageNo,int length);

    /**
     * 获取用户最近发布的10个POSTS
     * @param user
     * @return
     */
    List<Posts> getPostsByUser(User user);


    /**
     * 根据标签分页获取获取Posts
     * @param label
     * @return
     */
    Page<Posts> getPostsByLabel(Label label,int pageNo,int lenght);
}
