package com.quark.admin.service;

import com.quark.common.Base.BaseService;
import com.quark.common.entity.AdminUser;

/**
 * Created by lhr on 17-8-1.
 */

public interface AdminUserService extends BaseService<AdminUser>{

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    AdminUser findByUserName(String username);
}
