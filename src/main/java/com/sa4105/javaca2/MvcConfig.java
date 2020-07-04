package com.sa4105.javaca2;

import com.sa4105.javaca2.interceptor.SessionTimerInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
        super();
    }


    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new SessionTimerInterceptor()).excludePathPatterns("", "/", "dbseed", "/assets/**");
    }
}