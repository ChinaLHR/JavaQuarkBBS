package com.quark.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @Author LHR
 *
 * 配置webSocket服务，使用stomp协议
 * Create By 2017/9/6
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic","/user");//可以在topic，user域向客户端发送消息
        registry.setUserDestinationPrefix("/user/");//指定用户发送（一对一）的主题前缀是“/user/”
        registry.setApplicationDestinationPrefixes("/app");//客户端向服务端发送时的主题上面需要加"/app"作为前缀；
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/quarkServer").setAllowedOrigins("*").withSockJS();//stomp节点
    }
}
