package com.database.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.database.mapper")
public class MybatisPlusConfig {
    /**
     * 配置数据源
     */
    @Bean
    public DataSource dataSource(PropertyConfig propertyConfig){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(propertyConfig.getUser());
        dataSource.setPassword(propertyConfig.getPassword());
        dataSource.setUrl(propertyConfig.getUrl());
        dataSource.setDriverClassName(propertyConfig.getDriver());
        return dataSource;
    }

    /*@Bean
    public DataSource dataSource(PropertyConfig propertyConfig) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(propertyConfig.getUser());
        dataSource.setPassword(propertyConfig.getPassword());
        dataSource.setJdbcUrl(propertyConfig.getUrl());
        dataSource.setDriverClass(propertyConfig.getDriver());
        return dataSource;
    }*/

    /**
     * 获得getMybatisSqlSessionFactoryBean
     * @return
     */
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        MybatisSqlSessionFactoryBean mybatisPlus  = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dataSource);
        return mybatisPlus;
    }


    /**
     * 设置mapper文件的扫描路径
     * @return
     */
   /* @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.cxw.studentinfo,mapper");
        return mapperScannerConfigurer;
    }*/

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor getPaginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //自定义方言 可以没有
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

}