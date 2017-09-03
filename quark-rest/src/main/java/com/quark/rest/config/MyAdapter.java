package com.quark.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author LHR
 * Create By 2017/8/30
 */
@Configuration
public class MyAdapter extends WebMvcConfigurerAdapter {


    /**
     * 配置默认路径为文档
     * @param registry
     */
    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "" ).setViewName( "redirect:/swagger-ui.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }
}
