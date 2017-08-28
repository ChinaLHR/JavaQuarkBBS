package com.quark.rest.controller;

import com.quark.common.base.BaseController;
import com.quark.common.dto.QuarkPageResult;
import com.quark.common.dto.QuarkResult;
import com.quark.common.entity.Posts;
import com.quark.common.entity.User;
import com.quark.rest.service.PostsService;
import com.quark.rest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Author LHR
 * Create By 2017/8/26
 */
@Api(value = "帖子接口", description = "发布帖子,获取帖子")
@RestController
@RequestMapping("/posts")
public class PostsController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostsService postsService;

    @ApiOperation("发帖接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "帖子内容", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "帖子标题", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户令牌", dataType = "String"),
            @ApiImplicitParam(name = "labelId", value = "标签ID", dataType = "Integer")
    })
    @PostMapping
    public QuarkResult CreatePosts(Posts posts, String token, Integer labelId) {
        QuarkResult result = restProcessor(() -> {
            if (token == null) return QuarkResult.warn("请先登录！");

            User userbytoken = userService.getUserByToken(token);
            if (userbytoken == null) return QuarkResult.warn("用户不存在,请先登录！");

            User user = userService.findOne(userbytoken.getId());
            if (user.getEnable() != 1) return QuarkResult.warn("用户处于封禁状态！");

            postsService.SavePosts(posts, labelId, user);
            return QuarkResult.ok();
        });

        return result;
    }

    @ApiOperation("翻页查询帖子接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "查询条件", dataType = "int"),
            @ApiImplicitParam(name = "type", value = "帖子类型[top : good : ]", dataType = "String"),
            @ApiImplicitParam(name = "pageNo", value = "页码[从1开始]", dataType = "int"),
            @ApiImplicitParam(name = "length", value = "返回结果数量[默认20]", dataType = "int")
    })
    @GetMapping
    public QuarkPageResult GetPosts(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int length) {
        try {
            if (!type.equals("good") && !type.equals("top") && !type.equals(""))
                return QuarkPageResult.error("类型错误!");
            Page<Posts> page = postsService.getPostsByPage(type, search, pageNo - 1, length);
            return QuarkPageResult.ok(page.getContent(), page.getTotalElements(), page.getNumberOfElements());
        } catch (Exception e) {
            logger.error("Error Log :" + e.getLocalizedMessage(), e);
            return QuarkPageResult.error("服务器出现异常");
        }
    }

}
