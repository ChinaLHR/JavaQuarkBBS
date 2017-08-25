package com.quark.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author LHR
 * Create By 2017/8/20
 */
@Configuration
@ComponentScan(basePackages = "com.quark.common")
@ComponentScan(basePackages = "com.quark.rest")
public class RestConfig {

}
