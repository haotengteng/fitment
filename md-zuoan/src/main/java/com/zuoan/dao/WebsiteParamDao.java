package com.zuoan.dao;

import com.zuoan.module.WebsiteParam;

import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
public interface WebsiteParamDao {
    int addWebsiteParam(WebsiteParam websiteParamInfo);

    int delWebsiteParam(String websiteParamId);

    int updateWebsiteParam(WebsiteParam websiteParamInfo);

    List<WebsiteParam> selectWebsiteParam(WebsiteParam websiteParamInfo);

    WebsiteParam selectWebsiteParamById(String websiteParamId);

    WebsiteParam selectWebsiteParamByCode(String paramCode);
}
