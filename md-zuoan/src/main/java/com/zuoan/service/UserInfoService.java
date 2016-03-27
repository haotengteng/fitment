package com.zuoan.service;

import com.zuoan.module.UserInfo;
import com.zuoan.utils.mybatis.Page;

import java.util.List;

/**
 *
 * Created by htt on 2016/3/22.
 */
public interface UserInfoService {
    boolean addUserInfo(UserInfo userInfo);

    boolean delUserInfo(String userId);

    boolean updateUserInfo(UserInfo userInfo);

    List<UserInfo> queryUserInfo(UserInfo userInfo);

    Page<UserInfo> queryUserInfoByPage(UserInfo userInfo,Page page);

    UserInfo queryUserInfoById(String userId);

    UserInfo queryUserInfoByPhone(String phone);

    UserInfo queryUserInfoByUserName(String userName);
}
