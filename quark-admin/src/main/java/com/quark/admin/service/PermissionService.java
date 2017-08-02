package com.quark.admin.service;

import com.quark.common.Base.BaseService;
import com.quark.common.entity.Permission;

import java.util.List;

/**
 * Created by lhr on 17-8-1.
 */
public interface PermissionService extends BaseService<Permission>{

    List<Permission> loadUserPermission(Integer id);
    List<Permission> findByPage(int pageNo, int pageSize);
}
