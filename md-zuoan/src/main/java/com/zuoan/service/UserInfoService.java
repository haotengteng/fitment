package com.zuoan.service;

import com.zuoan.module.UserInfo;

/**
 * Created by htt on 2016/3/22.
 */
public interface UserInfoService {
    boolean addUserInfo(UserInfo userInfo);

    boolean delUserInfo(String userId);

    boolean updateUserInfo(UserInfo userInfo);

    UserInfo queryUserInfo(UserInfo userInfo);
}
