package com.zuoan.ApiProvider;

import com.zuoan.service.UserInfoService;

/**
 *
 * Created by htt on 2016/3/22.
 */
public class ApiProvider {
    public static final UserInfoService userInfoService;
    static {
        userInfoService = AppContextHolder.getApplicationContext().getBean(UserInfoService.class);
    }
}
