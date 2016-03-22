package com.zuoan.dao;

import com.zuoan.module.UserInfo;

/**
 * Created by htt on 2016/3/22.
 */
public interface UserInfoDao {
    int addUserInfo(UserInfo userInfo);

    int delUserInfo(String userId);

    int updateUserInfo(UserInfo userInfo);

    UserInfo selectUserInfo(UserInfo userInfo);
}
