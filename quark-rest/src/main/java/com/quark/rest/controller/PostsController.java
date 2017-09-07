package com.quark.rest.controller;

import com.quark.common.base.BaseController;
import com.quark.common.dto.QuarkResult;
import com.quark.common.entity.Label;
import com.quark.common.entity.Posts;
import com.quark.common.entity.Reply;
import com.quark.common.entity.User;
import com.quark.rest.service.LabelService;
import com.quark.rest.service.PostsService;
import com.quark.rest.service.ReplyService;
import com.quark.rest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    private LabelService labelService;

    @Autowired
    private PostsService postsService;

    @Autowired
    private ReplyService replyService;

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

            postsService.savePosts(posts, labelId, user);
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
    @GetMapping()
    public QuarkResult GetPosts(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "20") int length) {
        QuarkResult result = restProcessor(() -> {
            if (!type.equals("good") && !type.equals("top") && !type.equals(""))
                return QuarkResult.error("类型错误!");
            Page<Posts> page = postsService.getPostsByPage(type, search, pageNo - 1, length);
            return QuarkResult.ok(page.getContent(), page.getTotalElements(), page.getNumberOfElements());

        });

        return result;

    }


    @ApiOperation("翻页帖子详情与回复接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postsid", value = "帖子的id", dataType = "int"),
            @ApiImplicitParam(name = "pageNo", value = "页码[从1开始]", dataType = "int"),
            @ApiImplicitParam(name = "length", value = "返回结果数量[默认20]", dataType = "int")
    })
    @GetMapping("/detail/{postsid}")
    public QuarkResult GetPostsDetail(
            @PathVariable("postsid") Integer postsid,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "20") int length) {
        QuarkResult result = restProcessor(() -> {
            HashMap<String, Object> map = new HashMap<>();
            Posts posts = postsService.findOne(postsid);
            if (posts == null) return QuarkResult.error("帖子不存在");
            map.put("posts", posts);

            Page<Reply> page = replyService.getReplyByPage(postsid, pageNo - 1, length);
            map.put("replys", page.getContent());

            return QuarkResult.ok(map, page.getTotalElements(), page.getNumberOfElements());
        });
        return result;

    }

    @ApiOperation("根据labelId获取帖子接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "labelid", value = "标签的id", dataType = "int"),
            @ApiImplicitParam(name = "pageNo", value = "页码[从1开始]", dataType = "int"),
            @ApiImplicitParam(name = "length", value = "返回结果数量[默认20]", dataType = "int"),
    })
    @GetMapping("/label/{labelid}")
    public QuarkResult GetPostsByLabel(
            @PathVariable("labelid") Integer labelid,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "20") int length) {

        QuarkResult result = restProcessor(() -> {
            Label label = labelService.findOne(labelid);
            if (label == null) return QuarkResult.error("标签不存在");
            Page<Posts> page = postsService.getPostsByLabel(label, pageNo - 1, length);
            return QuarkResult.ok(page.getContent(), page.getTotalElements(), page.getNumberOfElements());

        });

        return result;

    }

}
