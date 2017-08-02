package com.quark.admin.service.impl;

import com.quark.admin.service.AdminUserService;
import com.quark.common.Base.BaseServiceImpl;
import com.quark.common.dao.AdminUserDao;
import com.quark.common.entity.AdminUser;
import org.springframework.stereotype.Service;

/**
 * Created by lhr on 17-8-1.
 */
@Service
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUserDao,AdminUser> implements AdminUserService{

    @Override
    public AdminUser findByUserName(String username) {
        return repository.findByUsername(username);
    }
}
