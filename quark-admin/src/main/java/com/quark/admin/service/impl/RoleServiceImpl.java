package com.quark.admin.service.impl;

import com.quark.admin.dto.QuarkAdminResult;
import com.quark.admin.service.AdminUserService;
import com.quark.admin.service.RoleService;
import com.quark.common.Base.BaseServiceImpl;
import com.quark.common.dao.RoleDao;
import com.quark.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by lhr on 17-8-1.
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService {

    @Autowired
    private AdminUserService userService;

    @Override
    public QuarkAdminResult findRolesAndSelected(Integer id) {
        Set<Role> userRole = userService.findOne(id).getRoles();
        List<Role> roles = findAll();
        for (Role r: roles) {
            if (userRole.contains(r)) r.setSelected(1);
        }

        return QuarkAdminResult.ok(roles);
    }
}
