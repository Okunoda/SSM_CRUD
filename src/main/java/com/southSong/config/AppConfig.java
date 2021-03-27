package com.southSong.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.southSong.controller.MybatisProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@MapperScan("/com/southSong/DAO")
//@PropertySource("/druid.properties")
@ComponentScan(basePackages = {"com.southSong.controller","com.southSong.service.Impl"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, Service.class})},
        useDefaultFilters = false)
@ImportResource("classpath:applicationContext.xml")
@EnableTransactionManagement
public class AppConfig {

    @Value("${mysql.username}")
    private String username;

    @Value("${mysql.password}")
    private String password;

    @Value("${mysql.url}")
    private String url;

    @Value("${mysql.driver}")
    private String driver;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("设置username");
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    配置事务管理
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    //   配置视图解析器
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("classpath:WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer getYml() {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(new ClassPathResource("/jdbc.yaml"));
        Properties properties = yamlPropertiesFactoryBean.getObject();
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setProperties(properties);
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
//        System.out.println(username);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setDriverClassName(driver);
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactoryBean getSession(DruidDataSource dataSource) throws IOException {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources("/mapper/*Mapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);
        return sqlSessionFactoryBean;
    }


}
