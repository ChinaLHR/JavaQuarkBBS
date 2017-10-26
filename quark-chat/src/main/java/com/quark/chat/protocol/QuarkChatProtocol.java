package com.quark.chat.protocol;

import com.alibaba.fastjson.JSONObject;
import com.quark.chat.utils.DateTimeUtil;
import com.quark.common.entity.User;

import java.util.HashMap;
import java.util.Set;

import static com.quark.chat.protocol.QuarkChatType.*;

/**
 * @Author : ChinaLHR
 * @Date : Create in 11:20 2017/10/22
 * @Email : 13435500980@163.com
 *
 *                QuarkChatProtocol
 *  __ __ __ __ __ __ __ __ __ __ __ ____ __ __ ____ __
 * |           |           |                          |
 *       4           1             Uncertainty
 * |__ __ __ __|__ __ __ __|__ __ __ __ __ __ __ __ __|
 * |           |           |                          |
 *     Magic         Type             Body
 *|__ __ __ __|__ __ __ __|__ __ __ __  __ __ __ __ __|
 *
 *
 *                 auth：result
 *                 SysyMsg：msg
 *                 ServerMsg：uid(userid) name icon time(create time)
 *                 UserInfo：count，Set<userinfo>
 */
public class QuarkChatProtocol {

    /**
     * Maginc
     */
    private static final int MAGIC = 0xabcd;

    /**
     * Type
     */
    private byte type;

    /**
     * body
     */
    private HashMap<String,Object> body;

    public QuarkChatProtocol(byte type) {
        this.type = type;
    }

    /**
     * Auth(server)
     * @param result
     * @return
     */
    public static String buildAuthProto(boolean result){
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", result);
        return buildProto(AUTH_RESPONSE_CODE,map);
    }

    /**
     * SysMessage
     * @param sysmsg
     * @return
     */
    public static String buildSysMessage(String sysmsg){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg",sysmsg);
        return buildProto(SYS_MESSAGE_CODE,map);
    }

    /**
     * SysUserInfo
     * @param users
     * @return
     */
    public static String buildSysUserInfo(Set<User> users){
        HashMap<String, Object> map = new HashMap<>();
        map.put("count",users.size());
        map.put("userinfo",users);
        return buildProto(SYS_USERSINFO_CODE,map);
    }

    /**
     * Ping
     * @return
     */
    public static String buildPing(){
        return buildProto(PING_CODE);
    }

    /**
     * Message(Server)
     * @return
     */
    public static String buildMessageCode(User user,String msg){
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid",user.getId());
        map.put("name",user.getUsername());
        map.put("icon",user.getIcon());
        map.put("time", DateTimeUtil.getCurrentDateTime());
        map.put("msg",msg);
        return buildProto(MESSAGE_RESPONSE_CODE,map);
    }
    /**
     * Proto生成
     * @param type
     * @return
     */
    private static String buildProto(byte type){
        QuarkChatProtocol protocol = new QuarkChatProtocol(type);
        return JSONObject.toJSONString(protocol);
    }

    private static String buildProto(byte type,HashMap<String,Object> map){
        QuarkChatProtocol protocol = new QuarkChatProtocol(type);
        protocol.body = map;
        return JSONObject.toJSONString(protocol);
    }
    public static int getMAGIC() {
        return MAGIC;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public HashMap<String, Object> getBody() {
        return body;
    }

    public void setBody(HashMap<String, Object> body) {
        this.body = body;
    }
}
