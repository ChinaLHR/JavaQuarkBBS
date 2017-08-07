package com.quark.admin.service.impl;

import com.quark.admin.service.RoleService;
import com.quark.common.Base.BaseServiceImpl;
import com.quark.common.dao.RoleDao;
import com.quark.common.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lhr on 17-8-1.
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleDao,Role> implements RoleService{
}
