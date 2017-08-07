package com.quark.admin.service.impl;

import com.quark.admin.service.PermissionService;
import com.quark.common.Base.BaseServiceImpl;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lhr on 17-8-1.
 */
@Service
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<PermissionDao, Permission> implements PermissionService {
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
                        perlist.addAll(role.getPermissions().stream().filter(p -> p.getParentId() > 0).collect(Collectors.toList()));
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
                        perlist.addAll(role.getPermissions().stream().filter(p -> p.getParentId() > 0&&p.getType()==type).collect(Collectors.toList()));
                    });
        }
        return perlist;
    }

    /**
     * 分页查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<Permission> findByPage(int pageNo, int pageSize) {
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order1);
        PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
        Page<Permission> page = repository.findAll(pageable);
        return page.getContent();
    }
}
