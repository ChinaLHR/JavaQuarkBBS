package com.quark.admin;

import com.quark.admin.service.AdminUserService;
import com.quark.admin.service.PermissionService;
import com.quark.admin.service.RoleService;
import com.quark.admin.utils.PasswordHelper;
import com.quark.common.entity.AdminUser;
import com.quark.common.entity.Permission;
import com.quark.common.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;

/**
 * Created by lhr on 17-7-31.
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:admin.properties"})
@SpringBootTest
public class AdminApplicationTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    AdminUserService adminUserService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleService roleService;

    @Test
    public void testDataSource() {
        List<Permission> permissions = permissionService.loadUserPermission(11);
        permissions.forEach(p->System.out.println(p.getName()));
    }
}
