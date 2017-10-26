package com.quark.chat.service;

import com.quark.chat.entity.ChatUser;
import com.quark.chat.protocol.QuarkChatProtocol;
import com.quark.chat.utils.BlankUtil;
import com.quark.chat.utils.NettyUtil;
import com.quark.common.entity.User;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author : ChinaLHR
 * @Date : Create in 22:00 2017/10/23
 * @Email : 13435500980@163.com
 */
@Service
public class ChannelManager {

    @Autowired
    private ChatService chatService;

    private static final Logger logger = LoggerFactory.getLogger(ChannelManager.class);

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    //维护Channel与ChatUser的关系
    private ConcurrentHashMap<Channel,ChatUser> chatUserMap = new ConcurrentHashMap<>();
    private AtomicInteger userCount = new AtomicInteger(0);

    /**
     * 添加Channel
     * @param channel
     */
    public void addChannel(Channel channel){
        String remoteAddr = NettyUtil.parseChannelRemoteAddr(channel);
        if (!channel.isActive())  logger.error("channel is not active, address: {}", remoteAddr);
        ChatUser chatUser = new ChatUser();
        chatUser.setAddr(remoteAddr);
        chatUser.setChannel(channel);
        chatUserMap.put(channel,chatUser);
    }

    /**
     * 认证用户
     * @param channel
     * @param token
     */
    public boolean authUser(String token,Channel channel){

        User user = chatService.getUserByToken(token);
        if (user==null)return false;
        boolean b = chatService.authUser(user.getId());
        if (b==false) return false;
        ChatUser chatUser = chatUserMap.get(channel);
        chatUser.setUser(user);
        chatUser.setAuth(true);
        chatUser.setTime(System.currentTimeMillis());

        //增加一个认证用户
        userCount.incrementAndGet();
        return true;
    }

    /**
     * 从缓存中移除Channel，并且关闭Channel
     * @param channel
     */
    public void removeChannel(Channel channel){
        try{
            logger.warn("channel will be remove, address is :{}", NettyUtil.parseChannelRemoteAddr(channel));
            rwLock.writeLock().lock();
            channel.close();
            ChatUser chatUser= chatUserMap.get(channel);
            if (chatUser!=null){
                ChatUser tmp = chatUserMap.remove(channel);
                if (tmp != null && tmp.isAuth()) {
                    // 减去一个认证用户
                    userCount.decrementAndGet();
                }
            }

        }finally {
            rwLock.writeLock().unlock();
        }
    }

    /**
     * 广播
     * @param buildmessage: 经过build的Protocol
     */
    public void broadMessage(String buildmessage){
        if (!BlankUtil.isBlank(buildmessage)){
            try {
                rwLock.readLock().lock();
                Set<Channel> keySet = chatUserMap.keySet();
                for (Channel ch : keySet) {
                    ChatUser cUser = chatUserMap.get(ch);
                    if (cUser == null || !cUser.isAuth()) continue;
                    ch.writeAndFlush(new TextWebSocketFrame(buildmessage));
                }
            }finally {
                rwLock.readLock().unlock();
            }
        }
    }

    /**
     * 心跳检测Ping
     */
    public void broadPing(){
        try{
            rwLock.readLock().lock();
            logger.info("broadCastPing userCount: {}", userCount.intValue());
            Set<Channel> keySet = chatUserMap.keySet();
            for (Channel ch : keySet) {
                ChatUser cUser = chatUserMap.get(ch);
                if (cUser == null || !cUser.isAuth()) continue;
                ch.writeAndFlush(new TextWebSocketFrame(QuarkChatProtocol.buildPing()));
            }
        }finally {
            rwLock.readLock().unlock();
        }
    }

    /**
     * 扫描并关闭失效的Channel
     */
    public void scanNotActiveChannel(){
        Set<Channel> keySet = chatUserMap.keySet();
        for (Channel ch : keySet) {
            ChatUser cUser = chatUserMap.get(ch);
            if (cUser==null) continue;
            if (!ch.isOpen() || !ch.isActive() || (!cUser.isAuth() &&
                    (System.currentTimeMillis() - cUser.getTime()) > 10000))
                removeChannel(ch);
        }
    }

    /**
     * 更新ChatUser心跳活跃时间
     * @param channel
     */
    public void updateUserTime(Channel channel) {
        ChatUser cUser = getChatUser(channel);
        if (cUser != null) {
            cUser.setTime(System.currentTimeMillis());
        }
    }

   public ChatUser getChatUser(Channel channel){return chatUserMap.get(channel);}

   public Set<User> getUsers(){
       HashSet<User> users = new HashSet<>();
       for (Channel channel : chatUserMap.keySet()) {
           users.add(chatUserMap.get(channel).getUser());
       }
       return users;
   }

    public Integer getUserCount() {
        return userCount.get();
    }


}
