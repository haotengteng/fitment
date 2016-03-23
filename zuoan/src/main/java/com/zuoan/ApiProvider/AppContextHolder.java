package com.zuoan.ApiProvider;

import org.springframework.context.ApplicationContext;

/**
 *
 * Created by htt on 2016/3/22.
 */
public class AppContextHolder {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        AppContextHolder.applicationContext = applicationContext;
    }
}
