package com.sa4105.javaca2;

import com.sa4105.javaca2.interceptor.SessionTimerInterceptor;
import com.sa4105.javaca2.utils.InterceptorWhitelist;

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
    	InterceptorWhitelist whitelist = InterceptorWhitelist.getInstance();
        registry.addInterceptor(new SessionTimerInterceptor()).excludePathPatterns(whitelist.paths);
    }
}