package com.quark.admin.service;

import com.quark.common.base.BaseService;
import com.quark.common.entity.Permission;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lhr on 17-8-1.
 */
public interface PermissionService extends BaseService<Permission>{

    /**
     * 根据用户id获取用户所有权限
     * @param id
     * @return
     */
    List<Permission> loadUserPermission(Integer id);

    /**
     * 根据用户id，type获取用户权限
     * @param id
     * @param type
     * @return
     */
    List<Permission> loadUserPermissionByType(Integer id,Integer type);

    /**
     * 根据角色ID查询用户权限
     * @param id
     * @return
     */
    List<Permission> findPermissionsAndSelected(Integer id);

    /**
     * 翻页查询
     * @param pageNo
     * @param length
     * @return
     */
    Page<Permission> findByPage(int pageNo, int length);


}
