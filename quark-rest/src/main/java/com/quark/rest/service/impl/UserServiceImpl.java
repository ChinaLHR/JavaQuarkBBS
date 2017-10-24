package com.quark.rest.service.impl;

import com.quark.common.base.BaseServiceImpl;
import com.quark.common.dao.UserDao;
import com.quark.common.entity.User;
import com.quark.common.exception.ServiceProcessException;
import com.quark.rest.service.RedisService;
import com.quark.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author LHR
 * Create By 2017/8/21
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private RedisService<Integer> redisSocketService;

    @Autowired
    private RedisService<User> redisService;

    @Value("${REDIS_USERID_KEY}")
    private String REDIS_USERID_KEY;

    @Value("${REDIS_USER_KEY}")
    private String REDIS_USER_KEY;

    @Value("${REDIS_USER_TIME}")
    private Integer REDIS_USER_TIME;

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
        user.setInitTime(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        repository.save(user);
    }

    @Override
    public String LoginUser(User user) {
        String token = UUID.randomUUID().toString();
        redisService.cacheString(REDIS_USER_KEY + token, user, REDIS_USER_TIME);
        redisSocketService.cacheSet(REDIS_USERID_KEY,user.getId());
//        loginId.add(user.getId());//维护一个登录用户的set
        return token;
    }

    @Override
    public User getUserByToken(String token) {
        User user = redisService.getStringAndUpDate(REDIS_USER_KEY + token, REDIS_USER_TIME);
        return user;
    }

    @Override
    public void LogoutUser(String token) {
        User user = getUserByToken(token);
        redisService.deleteString(REDIS_USER_KEY + token);
        redisSocketService.deleteSet(REDIS_USERID_KEY,user.getId());
//        loginId.remove(user.getId());//维护一个登录用户的set
    }

    @Override
    public void updateUser(String token, String username, String signature, Integer sex) {
        User cacheuser = redisService.getString(REDIS_USER_KEY + token);
        if (cacheuser == null) throw new ServiceProcessException("session过期,请重新登录");
        User user = repository.findOne(cacheuser.getId());
        user.setUsername(username);
        user.setSex(sex);
        user.setSignature(signature);
        repository.save(user);
        redisService.cacheString(REDIS_USER_KEY + token, user, REDIS_USER_TIME);
    }

    @Override
    public void updataUserIcon(String token, String icon) {
        User cacheuser = redisService.getString(REDIS_USER_KEY + token);
        if (cacheuser == null)
            throw new ServiceProcessException("用户Session过期，请重新登录");
        User user = repository.findOne(cacheuser.getId());
        user.setIcon(icon);
        repository.save(user);
        redisService.cacheString(REDIS_USER_KEY + token, user, REDIS_USER_TIME);
    }


    @Override
    public void updateUserPassword(String token, String oldpsd, String newpsd) {
        User cacheuser = redisService.getString(REDIS_USER_KEY + token);
        if (cacheuser == null)
            throw new ServiceProcessException("用户Session过期，请重新登录");
        User user = repository.findOne(cacheuser.getId());
        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(oldpsd.getBytes())))
            throw new ServiceProcessException("原始密码错误,请重新输入");
        user.setPassword(DigestUtils.md5DigestAsHex(newpsd.getBytes()));
        repository.save(user);
        redisService.deleteString(REDIS_USER_KEY+token);
    }


}
