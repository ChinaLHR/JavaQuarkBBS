package com.quark.porent.service.impl;

import com.quark.porent.entity.User;
import com.quark.porent.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:portal.properties"})
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Value("${cookie_name}")
    private String CookieName;

    @Test
    public void getUserByApi() throws Exception {
       User user =userService.getUserByApi("bee1a09b-9867-4f1a-9886-c25d8b0e42b1");
        System.out.println(user);
    }

    @Test
    public void getCookie() throws Exception {
        System.out.println(CookieName);
    }
}