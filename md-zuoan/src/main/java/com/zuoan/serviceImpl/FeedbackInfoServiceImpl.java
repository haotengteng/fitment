package com.zuoan.serviceImpl;

import com.zuoan.dao.FeedbackInfoDao;
import com.zuoan.module.FeedbackInfo;
import com.zuoan.service.FeedbackInfoService;
import com.zuoan.utils.mybatis.Page;
import com.zuoan.utils.mybatis.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
@Service
public class FeedbackInfoServiceImpl implements FeedbackInfoService {
    @Resource
    private FeedbackInfoDao feedbackInfoDao;
    @Override
    public boolean addFeedbackInfo(FeedbackInfo feedbackInfo) {
        if (feedbackInfo == null){
            return false;
        }
        return feedbackInfoDao.addFeedbackInfo(feedbackInfo)>0;
    }
    @Override
    public boolean delFeedbackInfo(String feedbackId) {
        if (StringUtils.isBlank(feedbackId)){
            return false;
        }
        if (feedbackInfoDao.selectFeedbackInfoById(feedbackId)==null){
            return false;
        }
        return  feedbackInfoDao.delFeedbackInfo(feedbackId)>0;
    }
    @Override
    public boolean updateFeedbackInfo(FeedbackInfo feedbackInfo) {
        if (feedbackInfo == null ||StringUtils.isBlank(feedbackInfo.getFeedbackId())){
            return false;
        }
        if (feedbackInfoDao.selectFeedbackInfoById(feedbackInfo.getFeedbackId())==null){
            return false;
        }
       return feedbackInfoDao.updateFeedbackInfo(feedbackInfo)>0;
    }
    @Override
    public List<FeedbackInfo> queryFeedbackInfo(FeedbackInfo feedbackInfo) {
        return feedbackInfoDao.selectFeedbackInfo(feedbackInfo);
    }

    @Override
    public Page<FeedbackInfo> queryFeedbackInfoByPage(FeedbackInfo feedbackInfo, Page page) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        feedbackInfoDao.selectFeedbackInfo(feedbackInfo);
        return PageHelper.endPage();
    }

    @Override
    public FeedbackInfo queryFeedbackInfoById(String feedbackId) {
        if (StringUtils.isBlank(feedbackId)){
            return null;
        }
        return feedbackInfoDao.selectFeedbackInfoById(feedbackId);
    }
}
