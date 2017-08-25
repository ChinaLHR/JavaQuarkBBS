package com.quark.admin.service;

import com.quark.common.base.BaseService;
import com.quark.common.entity.User;
import org.springframework.data.domain.Page;

/**
 * @Author LHR
 * Create By 2017/8/25
 */
public interface UserService  extends BaseService<User> {

    /**
     * 翻页获取用户列表
     * @param user
     * @param pageNo
     * @param length
     * @return
     */
    Page<User> findByPage(User user, int pageNo, int length);

    /**
     * 恢复/封禁用户
     * @param id
     */
    void saveUserEnable(Integer[] id);
}
