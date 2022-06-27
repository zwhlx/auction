package com.fifth.auction.config;

import com.fifth.auction.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/** 处理拦截器的注册 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {


    /** 配置拦截器 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单：存放在一个List集合
        List<String> patterns = new ArrayList<>();
        patterns.add("/css/**");
        patterns.add("/img/**");
        patterns.add("/js/**");
        patterns.add("/login.html");
        patterns.add("/register.html");
        patterns.add("/index.html");
        patterns.add("/users/reg");
        patterns.add("/users/islogin");
        patterns.add("/users/login");
        patterns.add("/auctions/get_all_auctions");
        patterns.add("/auctions/get_auction");
        patterns.add("/auctions/get_auctions_by_category");
        patterns.add("/auctions/get_auctions_by_isend");
        patterns.add("/auctions/isend");


        //完成拦截器的注册
        registry.addInterceptor(interceptor).addPathPatterns("/**") //要拦截的url是什么
                .excludePathPatterns(patterns);//排除
    }
}
