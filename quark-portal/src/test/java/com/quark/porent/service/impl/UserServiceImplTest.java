package com.quark.porent.service.impl;

import com.quark.porent.entity.User;
import com.quark.porent.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:portal.properties"})
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void getUserByApi() throws Exception {
       User user =userService.getUserByApi("6414a851-3510-4663-8eec-cc0a83d6b2d4");
        System.out.println(user);
    }

    @Test
    public void getCookie() throws Exception {

    }
}