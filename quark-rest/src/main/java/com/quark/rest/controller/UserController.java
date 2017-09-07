package com.quark.rest.controller;

import com.quark.common.base.BaseController;
import com.quark.common.dto.QuarkResult;
import com.quark.common.entity.Posts;
import com.quark.common.entity.User;
import com.quark.rest.service.NotificationService;
import com.quark.rest.service.PostsService;
import com.quark.rest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author LHR
 * Create By 2017/8/22
 */
@Api(value = "用户接口", description = "用户注册，登录，登出，获取用户信息等服务")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostsService postsService;

    @Autowired
    private NotificationService notificationService;

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

    @ApiOperation("根据Token获取用户的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "发送给用户的唯一令牌",dataType = "String"),
    })
    @GetMapping("/{token}")
    public QuarkResult getUserByToken(@PathVariable String token) {
        QuarkResult result = restProcessor(() -> {
            User user = userService.getUserByToken(token);
            if (user == null) return QuarkResult.warn("session过期,请重新登录");
            return QuarkResult.ok(user);
        });

        return result;
    }

    @ApiOperation("根据Token获取用户的信息与通知消息数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "发送给用户的唯一令牌",dataType = "String"),
    })
    @GetMapping("/message/{token}")
    public QuarkResult getUserAndMessageByToken(@PathVariable String token){
        QuarkResult result = restProcessor(() -> {
            HashMap<String, Object> map = new HashMap<>();
            User user = userService.getUserByToken(token);
            if (user == null) return QuarkResult.warn("session过期,请重新登录");
            long count = notificationService.getNotificationCount(user.getId());
            map.put("user",user);
            map.put("messagecount",count);
            return QuarkResult.ok(map);
        });

        return result;
    }

    @ApiOperation("根据Token修改用户的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "发送给用户的唯一令牌",dataType = "String"),
            @ApiImplicitParam(name = "username", value = "要修改的用户名",dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "用户签名",dataType = "String"),
            @ApiImplicitParam(name = "sex", value = "要修改的性别：数值0为男，1为女",dataType = "int"),
    })
    @PutMapping("/{token}")
    public QuarkResult updateUser(@PathVariable("token") String token,String username,String signature,Integer sex){
        QuarkResult result = restProcessor(() -> {
            if (!userService.checkUserName(username)) return QuarkResult.warn("用户名重复！");
            if (sex != 0 && sex != 1) return QuarkResult.warn("性别输入错误！");
            userService.updateUser(token, username, signature, sex);
            return QuarkResult.ok();
        });

        return result;
    }

    @ApiOperation("根据Token修改用户的密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "发送给用户的唯一令牌",dataType = "String"),
            @ApiImplicitParam(name = "newpsd",value = "新的密码",dataType = "String"),
            @ApiImplicitParam(name = "oldpsd",value = "新的密码",dataType = "String"),
    })
    @PutMapping("/password/{token}")
    public QuarkResult updatePassword(@PathVariable("token") String token,String newpsd,String oldpsd){

        QuarkResult result = restProcessor(() -> {
            userService.updateUserPassword(token,oldpsd,newpsd);
            return QuarkResult.ok();
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

    @ApiOperation("根据用户ID获取用户详情与用户最近发布的十个帖子[主要用于用户主页展示]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的id", dataType = "int")
    })
    @GetMapping("/detail/{userid}")
    public QuarkResult getUserById(@PathVariable("userid") Integer userid){
        QuarkResult result = restProcessor(() -> {
            User user = userService.findOne(userid);
            if (user == null || userid == null) return QuarkResult.warn("用户不存在");
            List<Posts> postss = postsService.getPostsByUser(user);
            HashMap<String, Object> map = new HashMap<>();
            map.put("posts",postss);
            map.put("user",user);
            return QuarkResult.ok(map);
        });
        return result;
    }


}
