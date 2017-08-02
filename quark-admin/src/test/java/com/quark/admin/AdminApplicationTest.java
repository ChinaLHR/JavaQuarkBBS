package com.quark.admin;

import com.quark.admin.service.AdminUserService;
import com.quark.admin.utils.PasswordHelper;
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
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations={"classpath:admin.properties"})
@SpringBootTest
public class AdminApplicationTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    AdminUserService adminUserService;

    @Test
    public void testDataSource(){
        AdminUser user = new AdminUser();
        user.setEnable(1);
        user.setUsername("lnx");
        user.setPassword("123456");
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        adminUserService.save(user);
    }
}
