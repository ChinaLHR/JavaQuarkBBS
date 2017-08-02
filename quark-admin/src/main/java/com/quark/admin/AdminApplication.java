package com.quark.admin;

import com.quark.common.CommonApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lhr on 17-7-31.
 */
@SpringBootApplication
@EnableCaching//缓存支持
public class AdminApplication {
    public static void main(String[] args) throws IOException {
        //更改properties配置文件名称,避免依赖冲突
        Properties properties = new Properties();
        InputStream in = AdminApplication.class.getClassLoader().getResourceAsStream("admin.properties");
        properties.load(in);
        SpringApplication app = new SpringApplication(AdminApplication.class);
        app.setDefaultProperties(properties);
        app.run(args);
//        SpringApplication.run(CommonApplication.class, args);
    }
}
