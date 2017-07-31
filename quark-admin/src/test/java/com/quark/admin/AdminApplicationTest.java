package com.quark.admin;

import com.quark.common.dao.AdminUserDao;
import com.quark.common.entity.AdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * Created by lhr on 17-7-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching//缓存支持
public class AdminApplicationTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    AdminUserDao adminUserDao;

    @Test
    public void testDataSource(){
        AdminUser adminUser = new AdminUser();
        adminUser.setPassword("999");
        adminUser.setUsername("dsada");
        adminUserDao.save(adminUser);
//        System.out.println(dataSource.getClass().getName());
    }
}
