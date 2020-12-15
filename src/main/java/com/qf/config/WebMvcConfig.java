package com.qf.config;

import com.qf.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 54110 on 2020/12/2.
 */
//配置类，在spring容器启动时进行加载
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public MyInterceptor getInterceptor() {
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor())
                .addPathPatterns("/game/addShopCart/**")
                .excludePathPatterns("/user/**");

    }


}
