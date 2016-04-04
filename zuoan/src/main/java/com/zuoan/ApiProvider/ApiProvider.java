package com.zuoan.ApiProvider;

import com.zuoan.service.*;
import com.zuoan.utils.redis.RedisCacheManage;

/**
 *
 * Created by htt on 2016/3/22.
 */
public class ApiProvider {
    public static final RedisCacheManage redisCacheManage;
    public static final UserInfoService userInfoService;
    public static final ProductTypeService productTypeService;
    public static final ProductInfoService productInfoService;
    public static final FeedbackInfoService feedbackInfoService;
    public static final WebsiteParamService websiteParamService;
    static {
        userInfoService = AppContextHolder.getApplicationContext().getBean(UserInfoService.class);
        productTypeService = AppContextHolder.getApplicationContext().getBean(ProductTypeService.class);
        productInfoService = AppContextHolder.getApplicationContext().getBean(ProductInfoService.class);
        redisCacheManage = AppContextHolder.getApplicationContext().getBean(RedisCacheManage.class);
        feedbackInfoService = AppContextHolder.getApplicationContext().getBean(FeedbackInfoService.class);
        websiteParamService = AppContextHolder.getApplicationContext().getBean(WebsiteParamService.class);
    }
}
