package com.fifth.auction.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**定义一个拦截器*/
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有重定向到登录页面
     * @param request current HTTP request 请求对象
     * @param response current HTTP response 响应对象
     * @param handler chosen handler to execute, for type and/or instance evaluation  处理器（url+Controller:映射）
     * @return 如果返回值为true表示放行当前的请求，如果返回值为false则表示拦截当前的请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //HttpServletRequest对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null){//说明用户没有登录过系统，则重定向到login.html页面
            response.sendRedirect("/login.html");
            return false;
        }
        //请求放行
        return true;
    }
}

