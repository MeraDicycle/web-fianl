package com.zjw.gmbackend.config;

import com.zjw.gmbackend.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**") // 默认全拦
                .excludePathPatterns(
                        // 登录注册相关
                        "/auth/login",
                        "/auth/register",

                        "/**/*.options",

                        // 公开浏览接口（你项目里这些通常不需要登录）
//                        "/music/recommend",
//                        "/music/tag",
//                        "/music/*",
//                        "/playlist/*",
//                        "/rank",
//                        "/search",

                        // swagger（如果你有）
                        "/swagger-ui/**",
                        "/v3/api-docs/**",

                        // 静态资源（如果有）
                        "/favicon.ico"
                );
    }
}

