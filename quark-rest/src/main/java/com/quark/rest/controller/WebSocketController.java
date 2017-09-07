package com.quark.rest.controller;

import com.quark.common.dto.SocketMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LHR
 * Create By 2017/9/6
 */

@Api(value = "WebSocket接口", description = "用于服务器端对客户都进行消息推送")
@RestController
public class WebSocketController {

    public SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @ApiOperation("WebSocket广播接口：客户端可以在/topic/all监听并接受服务端发回的消息")
    @SendTo("/topic/all")
    public SocketMessage sendToAll(SocketMessage message){
        return message;
    }

    @ApiOperation("WebSocket单播：客户端接收一对一消息的主题应该是“/user/” + 用户Id + “/message” ")
    @SendToUser("/message")
    public SocketMessage sendToUser(SocketMessage message){
        return message;
    }


}
