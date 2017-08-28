package com.quark.rest.controller;

import com.quark.common.base.BaseController;
import com.quark.common.dto.QuarkResult;
import com.quark.common.entity.User;
import com.quark.rest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author LHR
 * Create By 2017/8/22
 */
@Api(value = "用户接口", description = "用户注册，登录，登出，获取用户信息等服务")
@RestController
@RequestMapping("/user")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation("注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "用户邮箱",dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名称",dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码",dataType = "String")
    })
    @PostMapping
    public QuarkResult checkUserName(String email,String username,String password) {
        QuarkResult result = restProcessor(() -> {
            if (!userService.checkUserName(username))
                return QuarkResult.warn("用户名已存在，请重新输入");

            if (!userService.checkUserEmail(email))
                return QuarkResult.warn("用户邮箱已存在，请重新输入");

            else
                userService.createUser(email,username,password);

            return QuarkResult.ok();

        });
        return result;
    }

    @ApiOperation("登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "用户邮箱",dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码",dataType = "String")
    })
    @PostMapping("/login")
    public QuarkResult Login(String email,String password) {

        QuarkResult result = restProcessor(() -> {
            User loginUser = userService.findByEmail(email);
            if (loginUser == null)
                return QuarkResult.warn("用户邮箱不存在，请重新输入");
            if (!loginUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes())))
                return QuarkResult.warn("用户密码错误，请重新输入");
            String token = userService.LoginUser(loginUser);
            return QuarkResult.ok(token);
        });
        return result;
    }

    @ApiOperation("获取用户的信息")
    @GetMapping("/{token}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "发送给用户的唯一令牌",dataType = "String"),
    })
    public QuarkResult getUserByToken(@PathVariable String token) {
        QuarkResult result = restProcessor(() -> {
            User user = userService.getUserByToken(token);
            if (user == null) return QuarkResult.warn("session过期,请重新登录");
            return QuarkResult.ok(user);
        });

        return result;
    }

    @ApiOperation("登出用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "发送给用户的唯一令牌",dataType = "String")
    })
    @PostMapping("/logout")
    public QuarkResult logout(String token) {
        QuarkResult result = restProcessor(() -> {
            userService.LogoutUser(token);
            return QuarkResult.ok();
        });

        return result;
    }
}
