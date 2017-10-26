package com.quark.chat.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.quark.chat.entity.ChatUser;
import com.quark.chat.protocol.QuarkChatProtocol;
import com.quark.chat.protocol.QuarkClientProtocol;
import com.quark.chat.service.ChannelManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author : ChinaLHR
 * @Date : Create in 14:48 2017/10/24
 * @Email : 13435500980@163.com
 *
 * 消息处理Handler
 */
@ChannelHandler.Sharable
@Scope("prototype")
@Component
public class MessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    @Autowired
    private ChannelManager manager;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
        ChatUser chatUser = manager.getChatUser(ctx.channel());
        if (chatUser!=null&&chatUser.isAuth()){
            QuarkClientProtocol clientProto = JSON.parseObject(frame.text(), new TypeReference<QuarkClientProtocol>(){});
            //广播消息
            manager.broadMessage(QuarkChatProtocol.buildMessageCode(chatUser.getUser(),clientProto.getMsg()));
        }
    }

    /**
     * Channel取消注册
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        manager.removeChannel(ctx.channel());
        manager.broadMessage(QuarkChatProtocol.buildSysUserInfo(manager.getUsers()));
        super.channelUnregistered(ctx);
    }

    /**
     * Netty I/O异常事件
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("connection error and close the channel:{}",cause);
        manager.removeChannel(ctx.channel());
        manager.broadMessage(QuarkChatProtocol.buildSysUserInfo(manager.getUsers()));
    }
}
