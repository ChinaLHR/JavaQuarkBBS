package com.quark.chat.server;

import com.quark.chat.handler.MessageHandler;
import com.quark.chat.handler.UserAuthHandler;
import com.quark.chat.service.ChannelManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : ChinaLHR
 * @Date : Create in 15:06 2017/10/22
 * @Email : 13435500980@163.com
 */
@Component
public class QuarkChatServer implements Server {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private DefaultEventLoopGroup defaultGroup;
    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workGroup;
    private ChannelFuture future;
    private ServerBootstrap bootstrap;
    private ScheduledExecutorService executorService;

    @Value("${HOST}")
    private String host;

    @Value("${PORT}")
    private int port;

    @Autowired
    private UserAuthHandler authHandler;

    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private ChannelManager manager;

    @PostConstruct
    @Override
    public void init() {
        logger.info("server init");
        int cpus = Runtime.getRuntime().availableProcessors();

        defaultGroup = new DefaultEventLoopGroup(8, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "DEFAULTGROUP" + index.incrementAndGet());
            }
        });

        bossGroup = new NioEventLoopGroup(cpus, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "BOSSGROUP" + index.incrementAndGet());
            }
        });

        workGroup = new NioEventLoopGroup(cpus * 10, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "WORKGROUP" + index.incrementAndGet());
            }
        });

        bootstrap = new ServerBootstrap();
        executorService = Executors.newScheduledThreadPool(2);
    }

    @Override
    public void start() {
        logger.info("server start");
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)//TCP测链路检测
                .option(ChannelOption.TCP_NODELAY, true)//禁止使用Nagle算法
                .option(ChannelOption.SO_BACKLOG, 1024)//初始化服务端可连接队列大小
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(defaultGroup,
                                new HttpServerCodec(),//请求解码器
                                new HttpObjectAggregator(65536),//将多个消息转换成单一的消息对象
                                new ChunkedWriteHandler(),  //支持异步发送大的码流
                                new IdleStateHandler(60, 0, 0), //定时检测链路是否读空闲
                                authHandler,//认证Handler
                                messageHandler//消息Handler

                        );
                    }
                });

        try{
            future = bootstrap.bind().sync();
            InetSocketAddress addr = (InetSocketAddress) future.channel().localAddress();
            logger.info("QuarkChat start success ,host is :"+addr.getHostName()+",port is:"+addr.getPort());

            /**
             * 定时扫描Channel 关闭失效的Channel
             */
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    logger.info("scheduleAtFixedRate to close channel");
                    manager.scanNotActiveChannel();
                }
            },3,60, TimeUnit.SECONDS);//initialDelay：延迟三秒执行，period：任务执行的间隔周期

            /**
             * 定时向客户端发送Ping进行心跳检测
             */
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    logger.info("scheduleAtFixedRate to ping");
                    manager.broadPing();
                }
            },3,50,TimeUnit.SECONDS);
        }catch (InterruptedException e){
            logger.error("Quark Chat fail ",e);
            Thread.currentThread().interrupt();
        }
    }

    @PreDestroy
    @Override
    public void shutdown() {
        if (defaultGroup != null)
            defaultGroup.shutdownGracefully();
        if (executorService != null)
            executorService.shutdown();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

}
