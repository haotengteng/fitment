package com.zuoan.dao;

import com.zuoan.module.UserInfo;

import java.util.List;

/**
 * Created by htt on 2016/3/22.
 */
public interface UserInfoDao {
    int addUserInfo(UserInfo userInfo);

    int delUserInfo(String userId);

    int updateUserInfo(UserInfo userInfo);

    List<UserInfo> selectUserInfo(UserInfo userInfo);

    UserInfo selectUserInfoById(String userId);

    UserInfo selectUserInfoByPhone(String phone);
}
