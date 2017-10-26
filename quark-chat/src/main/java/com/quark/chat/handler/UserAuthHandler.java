package com.quark.chat.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.quark.chat.protocol.QuarkChatProtocol;
import com.quark.chat.protocol.QuarkClientProtocol;
import com.quark.chat.service.ChannelManager;
import com.quark.chat.utils.NettyUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.quark.chat.protocol.QuarkChatType.*;

/**
 * @Author : ChinaLHR
 * @Date : Create in 10:00 2017/10/24
 * @Email : 13435500980@163.com
 *
 * WebSocket握手/用户认证 Handler
 */
@ChannelHandler.Sharable
@Scope("prototype")
@Component
public class UserAuthHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthHandler.class);

    private WebSocketServerHandshaker handshaker;

    @Value("${WEBSOCKET_URL}")
    private String WEBSOCKET_URL;

    @Autowired
    private ChannelManager manager;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //Http请求（第一次握手）
        if (msg instanceof FullHttpRequest){
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
        //处理WebSocket请求
        else if (msg instanceof WebSocketFrame){
            handleWebSocket(ctx, (WebSocketFrame) msg);
        }
    }

    /**
     * 内部链路检测
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //当通道空闲时由IdleStateHandler触发的用户事件
        if (evt instanceof IdleStateEvent){
           IdleStateEvent event = (IdleStateEvent) evt;
            // 判断Channel是否读空闲, 读空闲时移除Channel
            if (event.state().equals(IdleState.READER_IDLE)) {
                final String address = NettyUtil.parseChannelRemoteAddr(ctx.channel());
                logger.warn("Netty Server UserAuthHandler: IDLE exception :{}", address);
                manager.removeChannel(ctx.channel());
                //广播用户数量
                manager.broadMessage(QuarkChatProtocol.buildSysUserInfo(manager.getUsers()));
            }
        }
    }

    /**
     * HTTP握手反馈
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request){
        //判断是否是WebSocket协议
        if (!request.decoderResult().isSuccess() || !"websocket".equals(request.headers().get("Upgrade"))) {
            logger.warn("protobuf don't support WebSocket");
            ctx.channel().close();
            return;
        }
        WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory(
                WEBSOCKET_URL, null, true);
        handshaker = handshakerFactory.newHandshaker(request);
        if (handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else {
            // 动态加入websocket的编解码处理
            handshaker.handshake(ctx.channel(), request);
            // 存储已经连接的Channel
            manager.addChannel(ctx.channel());
        }
    }

    /**
     * WebSocket反馈
     * @param ctx
     * @param frame
     */
    private void handleWebSocket(ChannelHandlerContext ctx,WebSocketFrame frame){
        //判断是否是关闭链路的命令
        if (frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            manager.removeChannel(ctx.channel());
            logger.info("Have a WebSocket Channel Close");
            return;
        }

        //判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame){
            logger.info("ping message : ",frame.content().retain());
            ctx.writeAndFlush(new PingWebSocketFrame(frame.content().retain()));
            return;
        }

        //判断是否是Pong消息
        if (frame instanceof PongWebSocketFrame){
            logger.info("pong message :",frame.content().retain());
            ctx.writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
        }

        //仅支持文本消息
        if (!(frame instanceof  TextWebSocketFrame)){
            throw new UnsupportedOperationException(frame.getClass().getName()+"frame type not supported!!!");
        }
        String message = ((TextWebSocketFrame)frame).text();
        QuarkClientProtocol clientProto = JSON.parseObject(message, new TypeReference<QuarkClientProtocol>(){});
        byte type = clientProto.getType();
        Channel channel = ctx.channel();
        if (clientProto.getMAGIC()!=QuarkChatProtocol.getMAGIC())return;//过滤Magic格式不正确的消息
        switch (type){
            case PING_CODE:
            case PONG_CODE:
                //接受到Pong消息后更新User的时间，便于定时清理过期掉线的用户
                manager.updateUserTime(channel);
                logger.info("receiver pong message address :{}",NettyUtil.parseChannelRemoteAddr(channel));
                return;
            case AUTH_REQUEST_CODE:
                //进行认证
                boolean isSuccess = manager.authUser(clientProto.getToken(), channel);
                manager.broadMessage(QuarkChatProtocol.buildAuthProto(isSuccess));
                if (isSuccess)
                    manager.broadMessage(QuarkChatProtocol.buildSysUserInfo(manager.getUsers()));
                return;
            case MESSAGE_REQUEST_CODE:
                break;//普通的消息留给MessageHandler处理
            default:
                logger.warn("The type [{}] can't be auth!!!", type);
                return;
        }

        //MessageHandler处理
        ctx.fireChannelRead(frame.retain());
    }
}
