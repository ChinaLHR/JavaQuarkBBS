package com.quark.rest.service;

import java.util.List;

/**
 * @Author LHR
 * Create By 2017/8/31
 */
public interface RankService {

    /**
     * 获取最近一周热帖排行榜
     * @return
     */
   List<Object> findPostsRank();

    /**
     * 获取最近一周的新注册用户
     * @return
     */
   List<Object> findUserRank();
}
