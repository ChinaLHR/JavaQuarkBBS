package com.quark.admin.service.impl;

import com.quark.admin.service.PermissionService;
import com.quark.admin.service.RoleService;
import com.quark.common.base.BaseServiceImpl;
import com.quark.common.dao.AdminUserDao;
import com.quark.common.dao.PermissionDao;
import com.quark.common.entity.AdminUser;
import com.quark.common.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by lhr on 17-8-1.
 */
@Service
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<PermissionDao, Permission> implements PermissionService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminUserDao adminUserDao;


    @Override
    public List<Permission> loadUserPermission(Integer id) {
        List<Permission> perlist = new ArrayList<>();
        AdminUser user = adminUserDao.findOne(id);
        if (user.getRoles().size() > 0) {
            user.getRoles().stream()
                    .filter(role -> role.getPermissions().size() > 0)
                    .forEach(role -> {
                        perlist.addAll(role.getPermissions().stream().filter(p -> p.getParentid() > 0).collect(Collectors.toList()));
                    });
        }
        return perlist;
    }

    @Override
    public List<Permission> loadUserPermissionByType(Integer id, Integer type) {
        List<Permission> perlist = new ArrayList<>();
        AdminUser user = adminUserDao.findOne(id);
        if (user.getRoles().size() > 0) {
            user.getRoles().stream()
                    .filter(role -> role.getPermissions().size() > 0)
                    .forEach(role -> {
                        perlist.addAll(role.getPermissions().stream().filter(p ->p.getParentid() > 0 && p.getType() == type)
                                .sorted(Comparator.comparing(Permission::getSort))
                                .collect(Collectors.toList()));
                    });
        }

        return perlist;
    }

    @Override
    public List<Permission> findPermissionsAndSelected(Integer id) {
        Set<Permission> permissions = roleService.findOne(id).getPermissions();
        List<Permission> all = repository.findAll();
        for (Permission p: all) {
            if (permissions.contains(p)) p.setChecked("true");
            else p.setChecked("false");
        }
        return all;
    }

    @Override
    public Page<Permission> findByPage(int pageNo, int length) {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "sort");
        Sort sort = new Sort(order);
        PageRequest pageRequest = new PageRequest(pageNo, length,sort);
        Page<Permission> page = repository.findAll(pageRequest);
        return page;
    }

}
