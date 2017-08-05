package com.quark.admin.service.impl;

import com.quark.admin.service.PermissionService;
import com.quark.common.Base.BaseServiceImpl;
import com.quark.common.dao.AdminUserDao;
import com.quark.common.dao.PermissionDao;
import com.quark.common.entity.AdminUser;
import com.quark.common.entity.Permission;
import com.quark.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by lhr on 17-8-1.
 */
@Service
@Transactional
public class PermissionServiceImpl  extends BaseServiceImpl<PermissionDao,Permission> implements PermissionService{
    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public List<Permission> loadUserPermission(Integer id) {
        List<Permission> perlist = new ArrayList<>();
        AdminUser user = adminUserDao.findOne(id);
        Set<Role> roles = user.getRoles();
        for(Role role : roles){
           perlist.addAll(role.getPermissions());
        }
        return perlist;
    }

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<Permission> findByPage(int pageNo, int pageSize) {
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order1);
        PageRequest pageable = new PageRequest(pageNo, pageSize,sort);
        Page<Permission> page = repository.findAll(pageable);
        return page.getContent();
    }
}
