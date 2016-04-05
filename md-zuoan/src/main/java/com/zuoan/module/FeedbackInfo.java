package com.zuoan.module;

/**
 *
 * Created by Administrator on 2016/3/22.
 */
public class FeedbackInfo extends Base {
    private String feedbackId;
    private String feedbackUserName;
    private String feedbackEmail;
    private String feedbackPhone;
    private String feedbackTitle;
    private String feedbackContent;

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackUserName() {
        return feedbackUserName;
    }

    public void setFeedbackUserName(String feedbackUserName) {
        this.feedbackUserName = feedbackUserName;
    }

    public String getFeedbackEmail() {
        return feedbackEmail;
    }

    public void setFeedbackEmail(String feedbackEmail) {
        this.feedbackEmail = feedbackEmail;
    }

    public String getFeedbackPhone() {
        return feedbackPhone;
    }

    public void setFeedbackPhone(String feedbackPhone) {
        this.feedbackPhone = feedbackPhone;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }
}
