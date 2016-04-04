package com.zuoan.service;

import com.zuoan.module.FeedbackInfo;
import com.zuoan.utils.mybatis.Page;

import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
public interface FeedbackInfoService {
    boolean addFeedbackInfo(FeedbackInfo feedbackInfo);

    boolean delFeedbackInfo(String feedbackId);

    boolean updateFeedbackInfo(FeedbackInfo feedbackInfo);

    List<FeedbackInfo> queryFeedbackInfo(FeedbackInfo feedbackInfo);

    Page<FeedbackInfo> queryFeedbackInfoByPage(FeedbackInfo feedbackInfo, Page page);

    FeedbackInfo queryFeedbackInfoById(String feedbackId);
}
