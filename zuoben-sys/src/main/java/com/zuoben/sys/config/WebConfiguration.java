package com.zuoben.sys.config;

import com.zuoben.handler.GlobalExceptionHandler;
import com.zuoben.sys.interceptor.UserAuthRestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author
 */
@Configuration
@Primary
@EnableSpringDataWebSupport
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserAuthRestInterceptor()).
                addPathPatterns(getIncludePathPatterns());
    }


    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

    /**
     * 需要用户鉴权的路径
     *
     * @return
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/sysMenu/**",
                "/sysUser/disable/**",
                "/sysUser/list/**",
                "/sysUser/insert/**",
                "/sysUser/update/**",
                "/sysUser/select/**",
                "/sysRole/**"
        };
        Collections.addAll(list, urls);
        return list;
    }

}
