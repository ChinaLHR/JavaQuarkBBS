package com.quark.admin;

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
public class AdminApplication {

//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//
//        return (container -> {
//            ErrorPage error403Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/403.html");
//            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/templates/404.html");
//            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
//
//            container.addErrorPages(error403Page, error404Page, error500Page);
//        });
//    }


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
