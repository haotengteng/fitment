package com.zuoan.dao;

import com.zuoan.module.FeedbackInfo;

import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
public interface FeedbackInfoDao {
    int addFeedbackInfo(FeedbackInfo feedbackInfo);

    int delFeedbackInfo(String feedbackId);

    int updateFeedbackInfo(FeedbackInfo feedbackInfo);

    List<FeedbackInfo> selectFeedbackInfo(FeedbackInfo feedbackInfo);

    FeedbackInfo selectFeedbackInfoById(String feedbackId);
}
