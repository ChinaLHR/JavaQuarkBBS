package com.quark.rest.service;

import com.quark.common.base.BaseService;
import com.quark.common.entity.User;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author LHR
 * Create By 2017/8/21
 */
public interface UserService extends BaseService<User> {

//    Set<Integer> loginId = ConcurrentHashMap.<Integer>newKeySet();

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

    /**
     * 根据Email查询用户
     * @param email
     * @return
     */
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


    /**
     * 修改用户信息
     * @param token
     * @param username
     * @param signature
     * @param sex
     */
    void updateUser(String token,String username,String signature,Integer sex );


    /**
     * 修改用户的头像
     * @param token
     * @param icon
     */
    void updataUserIcon(String token,String icon);

    /**
     * 修改用户的密码
     * @param token
     * @param oldpsd
     * @param newpsd
     */
    void updateUserPassword(String token,String oldpsd,String newpsd);


}
