package com.quark.admin.service.impl;

import com.quark.admin.service.AdminUserService;
import com.quark.admin.service.PermissionService;
import com.quark.admin.service.RoleService;
import com.quark.common.base.BaseServiceImpl;
import com.quark.common.dao.RoleDao;
import com.quark.common.dto.QuarkResult;
import com.quark.common.entity.Permission;
import com.quark.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/**
 * Created by lhr on 17-8-1.
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService {

    @Autowired
    private AdminUserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public QuarkResult findRolesAndSelected(Integer id) {
        Set<Role> userRole = userService.findOne(id).getRoles();
        List<Role> roles = findAll();
        for (Role r: roles) {
            if (userRole.contains(r)) r.setSelected(1);
        }

        return QuarkResult.ok(roles);
    }

    @Override
    public Page<Role> findByPage(int pageNo, int length) {
        PageRequest pageRequest = new PageRequest(pageNo, length);
        Page<Role> page = repository.findAll(pageRequest);
        return page;
    }

    @Override
    public void saveRolePermission(Integer roleid, Permission[] pers) {
        Role role = findOne(roleid);
        if (pers==null){
            role.setPermissions(new HashSet<>());
        }
        else {
            role.setPermissions(Stream.of(pers).collect(toSet()));
        }
        save(role);
    }
}
