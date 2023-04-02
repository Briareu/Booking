package com.database.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;


@Configuration
@ComponentScan(basePackages = {"com.database"},excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class),
})
/**
 * 核心配置文件
 */
public class AppConfig {

}