package com.zuoan.filter;

import com.zuoan.ApiProvider.ApiProvider;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Created by haotengteng on 2016/3/27.
 */
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String requestURI = httpServletRequest.getRequestURI();
        if (requestURI.startsWith("zuoan/user/")) {
            Cookie[] cookies = httpServletRequest.getCookies();
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    if (ApiProvider.redisCacheManage.getValue(cookie.getValue()) != null) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        //跳转到登陆界面
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
