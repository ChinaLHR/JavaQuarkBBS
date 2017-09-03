package com.quark.rest.service.impl;

import com.quark.common.dao.PostsDao;
import com.quark.common.dao.UserDao;
import com.quark.rest.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author LHR
 * Create By 2017/8/31
 */
@Service
public class RankServiceImpl  implements RankService{
    @Autowired
    private PostsDao postsDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Object> findPostsRank() {
        return postsDao.findHot();
    }

    @Override
    public List<Object> findUserRank() {
        return userDao.findNewUser();
    }
}
