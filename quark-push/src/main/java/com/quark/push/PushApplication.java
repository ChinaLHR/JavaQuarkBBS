package com.quark.push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author : ChinaLHR
 * @Date : Create in 21:49 2017/10/19
 * @Email : 13435500980@163.com
 */
@SpringBootApplication
public class PushApplication {

    //TODO 待实现：WebSocket推送分割与整个项目的服务化
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        InputStream in = PushApplication.class.getClassLoader().getResourceAsStream("push.properties");
        properties.load(in);
        SpringApplication app = new SpringApplication(PushApplication.class);
        app.setDefaultProperties(properties);
        app.run(args);
    }

}
