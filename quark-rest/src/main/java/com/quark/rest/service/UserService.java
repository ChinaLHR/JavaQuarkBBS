package com.quark.rest.service;

import com.quark.common.base.BaseService;
import com.quark.common.entity.User;

/**
 * @Author LHR
 * Create By 2017/8/21
 */
public interface UserService extends BaseService<User> {

    /**
     * 验证用户名是否可以使用
     *
     * @param username
     * @return
     */
    boolean checkUserName(String username);

    /**
     * 验证用户邮箱
     *
     * @param email
     * @return
     */
    boolean checkUserEmail(String email);

    User findByEmail(String email);

    /**
     * 注册用户
     * @param email
     * @param username
     * @param password
     */
    void createUser(String email,String username,String password);

    /**
     * 用户登录
     *
     * @param user
     * @return 返回token
     */
    String LoginUser(User user);


    /**
     * 通过Token获取用户信息
     * @param token
     * @return
     */
    User getUserByToken(String token);

    /**
     * 根据Token登出用户
     * @param token
     */
    void LogoutUser(String token);

}
