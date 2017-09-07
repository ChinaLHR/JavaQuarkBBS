package com.quark.rest.controller;

import com.quark.common.base.BaseController;
import com.quark.common.dto.QuarkResult;
import com.quark.common.entity.Reply;
import com.quark.common.entity.User;
import com.quark.rest.service.ReplyService;
import com.quark.rest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LHR
 * Create By 2017/8/30
 */
@Api(value = "回复接口", description = "对帖子进行回复,点赞回复等服务")
@RestController
@RequestMapping("/reply")
public class ReplyController extends BaseController{

    @Autowired
    private WebSocketController webSocketController;

    @Autowired
    private UserService userService;

    @Autowired
    private ReplyService replyService;
    @ApiOperation("发布回复接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "回复内容", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户令牌", dataType = "String"),
            @ApiImplicitParam(name = "postsId", value = "帖子ID", dataType = "Integer")
    })
    @PostMapping
    public QuarkResult CreateReply(Reply reply,Integer postsId,String token){
        QuarkResult result = restProcessor(() -> {
            if (token == null) return QuarkResult.warn("请先登录！");

            User userbytoken = userService.getUserByToken(token);
            if (userbytoken == null) return QuarkResult.warn("用户不存在,请先登录！");

            User user = userService.findOne(userbytoken.getId());
            if (user.getEnable() != 1) return QuarkResult.warn("用户处于封禁状态！");

            replyService.saveReply(reply, postsId, user);
            return QuarkResult.ok();
        });
        return result;
    }


}
