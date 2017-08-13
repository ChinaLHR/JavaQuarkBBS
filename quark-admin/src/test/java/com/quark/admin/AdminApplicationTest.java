package com.quark.admin;

import com.quark.admin.service.AdminUserService;
import com.quark.admin.service.PermissionService;
import com.quark.admin.service.RoleService;
import com.quark.admin.utils.PasswordHelper;
import com.quark.common.dao.AdminUserDao;
import com.quark.common.entity.AdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * Created by lhr on 17-7-31.
 * user.setUsername("lhr");
 * user.setPassword("root");
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:admin.properties"})
@SpringBootTest
public class AdminApplicationTest {

    @Autowired
    DataSource dataSource;

    @Autowired(required=true)
    AdminUserService adminUserService;

    @Autowired(required=true)
    PermissionService permissionService;

    @Autowired(required=true)
    RoleService roleService;

    @Autowired
    AdminUserDao dao;

    @Test
    public void testDataSource() {
        for (int i = 20; i < 40; i++) {
            AdminUser user = new AdminUser();
            user.setEnable(1);
            user.setUsername("测试"+i);
            user.setPassword("123456");
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            adminUserService.save(user);
        }

    }



    @Test
    public void testRole(){
//        AdminUser user1 = new AdminUser();
//        AdminUser user2 = new AdminUser();
//        AdminUser user3 = new AdminUser();
//        user1.setId(11);
//        user2.setId(12);
//        user3.setId(13);
        adminUserService.saveAdminEnable(new Integer[]{11,12,13});
    }
}
