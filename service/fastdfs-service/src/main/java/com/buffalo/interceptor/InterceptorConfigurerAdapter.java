package com.buffalo.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfigurerAdapter extends WebMvcConfigurerAdapter {
	/**
     * 该方法用于注册拦截器
     * 可注册多个拦截器，多个拦截器组成一个拦截器链
     */
    /**
     * Api拦截器
     * @return
     */
    @Bean
    public ApiInterceptor apiInterceptor() {
        return new ApiInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 添加路径
        // excludePathPatterns 排除路径
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


}
