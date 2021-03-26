package com.southSong.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("/druid.properties")
@MapperScan("com/southSong/DAO")
public class AppConfig {

    @Bean
    public DruidDataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("username");
        druidDataSource.setUrl("url");
        druidDataSource.setDriverClassName("driver");
        return druidDataSource;
    }
    @Bean
    public SqlSessionFactoryBean getSession(){

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource());
        sqlSessionFactoryBean.setMapperLocations(new Resource[]{new ClassPathResource("/mapper/*Mapper.xml")});
        return sqlSessionFactoryBean;
    }
}
