package com.zuoan.serviceImpl;

import com.zuoan.dao.UserInfoDao;
import com.zuoan.module.UserInfo;
import com.zuoan.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by htt on 2016/3/22.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    public boolean addUserInfo(UserInfo userInfo) {
        if (userInfo == null || StringUtils.isBlank(userInfo.getUserId())){
            return false;
        }
        return userInfoDao.addUserInfo(userInfo)>0;
    }

    public boolean delUserInfo(String userId) {
        if (StringUtils.isBlank(userId)){
            return false;
        }
        if (userInfoDao.selectUserInfoById(userId)==null){
            return false;
        }
        return  userInfoDao.delUserInfo(userId)>0;
    }

    public boolean updateUserInfo(UserInfo userInfo) {
        if (userInfo == null ||StringUtils.isBlank(userInfo.getUserId())){
            return false;
        }
        if (userInfoDao.selectUserInfoById(userInfo.getUserId())==null){
            return false;
        }
       return userInfoDao.updateUserInfo(userInfo)>0;
    }

    public List<UserInfo> queryUserInfo(UserInfo userInfo) {
        if (userInfo == null || StringUtils.isBlank(userInfo.getUserId())){
            return null;
        }
        return userInfoDao.selectUserInfo(userInfo);
    }

    public UserInfo queryUserInfoById(String userId) {
        if (StringUtils.isBlank(userId)){
            return null;
        }
        return userInfoDao.selectUserInfoById(userId);
    }

    public UserInfo queryUserInfoByPhone(String phone) {
        if (StringUtils.isBlank(phone)){
            return null;
        }
        return userInfoDao.selectUserInfoByPhone(phone);
    }

    public UserInfo queryUserInfoByUserName(String userName) {
        if (StringUtils.isBlank(userName)){
            return null;
        }
        return userInfoDao.selectUserInfoByUserName(userName);
    }
}
