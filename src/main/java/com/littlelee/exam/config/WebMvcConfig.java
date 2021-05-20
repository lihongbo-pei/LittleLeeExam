package com.littlelee.exam.config;

import com.littlelee.exam.interceptor.admin.SystemLoginInterceptor;
import com.littlelee.exam.interceptor.home.HomeLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author littlelee
 * @date 2021/4/11 20:05
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private SystemLoginInterceptor systemLoginInterceptor;

    @Autowired
    private HomeLoginInterceptor homeLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(systemLoginInterceptor)
                .excludePathPatterns("/system/login", "/system/get_cpacha", "/admin/**")
                .addPathPatterns("/system/*", "/admin/**");
        registry.addInterceptor(homeLoginInterceptor)
                .excludePathPatterns("/home/user/menu.html", "/home/user/head.html")
                .addPathPatterns("/home/user/**", "/home/exam/*");
    }
}
