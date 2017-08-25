package com.quark.rest;

import com.quark.common.dao.AdminUserDao;
import com.quark.rest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * Created by lhr on 17-7-31.
 * user.setUsername("lhr");
 * user.setPassword("root");
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:rest.properties"})
@SpringBootTest
public class RestApplicationTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserService userService;

    @Autowired
    DataSource dataSource;

    @Autowired
    AdminUserDao dao;

    @Value( "${Redis_User_Key}" )
    public String userKey;

    @Test
    public void testDataSource() {
        for (int i = 30; i < 60; i++) {
           userService.createUser(String.valueOf(i)+"tulin@gmail.com","图灵"+i+"号","123456");
        }
    }

}
