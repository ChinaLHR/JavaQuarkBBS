package com.quark.admin.service;

import com.quark.common.base.BaseService;
import com.quark.common.dto.QuarkResult;
import com.quark.common.entity.Permission;
import com.quark.common.entity.Role;
import org.springframework.data.domain.Page;

/**
 * Created by lhr on 17-8-1.
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 根据用户id查询用户的角色
     *
     * @param id
     * @return
     */
    QuarkResult findRolesAndSelected(Integer id);

    /**
     * 翻页查询
     * @param pageNo
     * @param length
     * @return
     */
    Page<Role> findByPage(int pageNo, int length);

    /**
     * 保存角色的权限
     * @param roleid
     * @param pers
     */
    void saveRolePermission(Integer roleid, Permission[] pers);
}
