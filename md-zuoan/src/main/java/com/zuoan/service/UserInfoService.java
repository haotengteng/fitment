package com.zuoan.service;

import com.zuoan.module.UserInfo;

import java.util.List;

/**
 * Created by htt on 2016/3/22.
 */
public interface UserInfoService {
    boolean addUserInfo(UserInfo userInfo);

    boolean delUserInfo(String userId);

    boolean updateUserInfo(UserInfo userInfo);

    List<UserInfo> queryUserInfo(UserInfo userInfo);

    UserInfo queryUserInfoById(String userId);

    UserInfo queryUserInfoByPhone(String phone);
}
