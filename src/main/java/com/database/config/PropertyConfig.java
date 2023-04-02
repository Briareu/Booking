package com.database.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:db.properties")
@Data
@Configuration
public class PropertyConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver}")
    private String driver;
    @Value("${spring.datasource.user}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

}
