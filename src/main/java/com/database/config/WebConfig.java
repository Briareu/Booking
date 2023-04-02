package com.database.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.database",
    includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
    value = Controller.class)})
@EnableWebMvc
/**
 * springmvc配置文件
 */
public class WebConfig {

}
