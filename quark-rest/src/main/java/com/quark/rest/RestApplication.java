package com.quark.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lhr on 17-7-31.
 */
@SpringBootApplication
@EnableCaching//缓存支持
public class RestApplication {

    public static void main(String[] args) throws IOException {
        //更改properties配置文件名称,避免依赖冲突
        Properties properties = new Properties();
        InputStream in = RestApplication.class.getClassLoader().getResourceAsStream("rest.properties");
        properties.load(in);
        SpringApplication app = new SpringApplication(RestApplication.class);
        app.setDefaultProperties(properties);
        app.run(args);
    }
}
