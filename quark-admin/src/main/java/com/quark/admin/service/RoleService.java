package com.quark.admin.service;

import com.quark.admin.dto.QuarkAdminResult;
import com.quark.common.Base.BaseService;
import com.quark.common.entity.Role;

/**
 * Created by lhr on 17-8-1.
 */
public interface RoleService extends BaseService<Role> {

    QuarkAdminResult findRolesAndSelected(Integer id);
}
