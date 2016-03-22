package com.zuoan.application;

import com.zuoan.ApiProvider.AppContextHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.ApplicationPath;

/**
 * Created by htt on 2016/3/22.
 */
@ApplicationPath("/*")
public class ZuoanApplication extends ResourceConfig {


    public ZuoanApplication(){
        packages("com.zuoan");

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:config\\md-zuoan-application.xml");
        AppContextHolder appContextHolder = new AppContextHolder();
        appContextHolder.setApplicationContext(applicationContext);
    }
}
