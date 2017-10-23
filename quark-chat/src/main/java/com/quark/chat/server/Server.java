package com.quark.chat.server;

/**
 * @Author : ChinaLHR
 * @Date : Create in 10:41 2017/10/22
 * @Email : 13435500980@163.com
 * 服务端接口
 */
public interface Server {

    /**
     * 初始化服务器
     */
    void init();

    /**
     * 启动服务器
     */
    void start();

    /**
     * 关闭服务器
     */
    void shutdown();
}
