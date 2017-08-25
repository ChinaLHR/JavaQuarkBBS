package com.quark.rest.service.impl;

import com.quark.common.base.BaseServiceImpl;
import com.quark.common.dao.UserDao;
import com.quark.common.entity.User;
import com.quark.rest.service.UserService;
import com.quark.rest.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @Author LHR
 * Create By 2017/8/21
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean checkUserName(String username) {
        User user = repository.findByUsername(username);
        if (user == null) return true;
        return false;
    }

    @Override
    public boolean checkUserEmail(String email) {
        User user = repository.findByEmail(email);
        if (user == null) return true;
        return false;
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void createUser(String email, String username, String password) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setIcon("/");//设置默认图片
        user.setInitTime(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        repository.save(user);
    }

    @Override
    public String LoginUser(User user) {
        RedisUtils<User> redisUtils = new RedisUtils<>(redisTemplate);
        String token = UUID.randomUUID().toString();

        redisUtils.CacheSet(token, user);
        return token;
    }

    @Override
    public User getUserByToken(String token) {
        RedisUtils<User> redisUtils = new RedisUtils<>(redisTemplate);
        User user = redisUtils.getSet(token);
        return user;
    }

    @Override
    public void LogoutUser(String token) {
        RedisUtils<User> redisUtils = new RedisUtils<>(redisTemplate);
        redisUtils.deleteSet(token);
    }
}
