package com.southSong.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.*;

import javax.naming.ldap.Control;


@Configuration
@ComponentScan(basePackages = {"com.southSong.controller"},
        useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter( type = FilterType.ANNOTATION , classes = Controller.class),
        })
@ImportResource("classpath:mvcConfig.xml")
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurationSupport  {

//    //   配置视图解析器
//    @Override
//    protected void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp("WEB-INF/view/",".jsp");
//    }

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/**").addResourceLocations("file:D:\\project\\SSM-CRUD\\src\\main\\webapp\\static\\");
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

}
