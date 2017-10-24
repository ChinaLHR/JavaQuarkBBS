package com.quark.chat.protocol;

/**
 * @Author : ChinaLHR
 * @Date : Create in 14:26 2017/10/24
 * @Email : 13435500980@163.com
 */
public class QuarkClientProtocol {

    /**
     * Maginc
     */
    private int MAGIC;

    /**
     * Type
     */
    private byte type;

    /**
     * token
     */
    private String token;

    /**
     * msg
     */
    private String msg;

    public int getMAGIC() {
        return MAGIC;
    }

    public void setMAGIC(int MAGIC) {
        this.MAGIC = MAGIC;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
