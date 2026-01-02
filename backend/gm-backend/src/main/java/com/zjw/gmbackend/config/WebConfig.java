package com.zjw.gmbackend.config;

import com.zjw.gmbackend.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        // 登录注册
                        "/auth/login",
                        "/auth/register",

                        // 静态资源（⭐关键）
                        "/audio/**",
                        "/**/*.mp3",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.css",
                        "/**/*.js",

                        // swagger
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/audio/**")
                .addResourceLocations("classpath:/static/audio/");
    }
}


