package com.zuoan.serviceImpl;

import com.zuoan.dao.UserInfoDao;
import com.zuoan.module.UserInfo;
import com.zuoan.service.UserInfoService;
import com.zuoan.utils.mybatis.Page;
import com.zuoan.utils.mybatis.PageHelper;
import com.zuoan.utils.redis.RedisCacheable;
import com.zuoan.utils.redis.RedisCacheableForDelete;
import com.zuoan.utils.redis.RedisCacheableForUpdate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
        if (userInfo == null || StringUtils.isBlank(userInfo.getUserId())) {
            return false;
        }
        return userInfoDao.addUserInfo(userInfo) > 0;
    }

    @RedisCacheableForDelete
    public boolean delUserInfo(String userId) {
        if (StringUtils.isBlank(userId)) {
            return false;
        }
        if (userInfoDao.selectUserInfoById(userId) == null) {
            return false;
        }
        return userInfoDao.delUserInfo(userId) > 0;
    }

    @RedisCacheableForUpdate
    public boolean updateUserInfo(UserInfo userInfo) {
        if (userInfo == null || StringUtils.isBlank(userInfo.getUserId())) {
            return false;
        }
        if (userInfoDao.selectUserInfoById(userInfo.getUserId()) == null) {
            return false;
        }
        return userInfoDao.updateUserInfo(userInfo) > 0;
    }

    public List<UserInfo> queryUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            return null;
        }
        return userInfoDao.selectUserInfo(userInfo);
    }

    @Override
    public Page<UserInfo> queryUserInfoByPage(UserInfo userInfo, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        userInfoDao.selectUserInfo(userInfo);
        return PageHelper.endPage();
    }

    @RedisCacheable
    public UserInfo queryUserInfoById(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        return userInfoDao.selectUserInfoById(userId);
    }

    public UserInfo queryUserInfoByPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return null;
        }
        return userInfoDao.selectUserInfoByPhone(phone);
    }

    public UserInfo queryUserInfoByUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return null;
        }
        return userInfoDao.selectUserInfoByUserName(userName);
    }
}
