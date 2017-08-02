package com.quark.admin.service.impl;

import com.quark.admin.service.RoleService;
import com.quark.common.Base.BaseServiceImpl;
import com.quark.common.dao.RoleDao;
import com.quark.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by lhr on 17-8-1.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao,Role> implements RoleService{
}
