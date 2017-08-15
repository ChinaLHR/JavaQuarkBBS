package com.quark.admin.service;

import com.quark.admin.dto.QuarkAdminResult;
import com.quark.common.Base.BaseService;
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
    QuarkAdminResult findRolesAndSelected(Integer id);

    /**
     * 翻页查询
     * @param role
     * @param pageNo
     * @param length
     * @return
     */
    Page<Role> findByPage(Role role, int pageNo, int length);

    /**
     * 保存角色的权限
     * @param roleid
     * @param pers
     */
    void saveRolePermission(Integer roleid, Permission[] pers);
}
