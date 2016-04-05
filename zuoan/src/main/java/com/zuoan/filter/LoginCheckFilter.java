package com.zuoan.filter;

import com.zuoan.ApiProvider.ApiProvider;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 *
 * Created by haotengteng on 2016/3/27.
 */
@Provider
public class LoginCheckFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        // 路径需要配置(配置哪些不需要登录的)
        if (!(path.startsWith("account")
                || path.startsWith("application.wadl")
                || path.startsWith("param/code")
                || path.startsWith("feedback"))) {
            Cookie cookie = requestContext.getCookies().get("token");
            if (cookie != null && "token".equals(cookie.getName())) {
                if (ApiProvider.redisCacheManage.getValue(cookie.getValue()) == null) {
                    //跳转到登陆界面
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            } else {
                //跳转到登陆界面
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }
}
