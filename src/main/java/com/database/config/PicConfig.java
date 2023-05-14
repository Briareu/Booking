package com.database.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PicConfig implements WebMvcConfigurer {
	
	@Value("${file-upload-path}")
	private String pic_url;
	
	/*
	*addResourceHandler:访问映射路径
	*addResourceLocations:资源绝对路径
	*/
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations(pic_url);
    }
}
