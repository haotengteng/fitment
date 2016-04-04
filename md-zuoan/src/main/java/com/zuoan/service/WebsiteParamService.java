package com.zuoan.service;

import com.zuoan.module.WebsiteParam;
import com.zuoan.utils.mybatis.Page;

import java.util.List;

/**
 *
 * Created by xujy on 2016/3/26.
 */
public interface WebsiteParamService {
    boolean addWebsiteParam(WebsiteParam websiteParam);

    boolean delWebsiteParam(String paramId);

    boolean updateWebsiteParam(WebsiteParam websiteParam);

    List<WebsiteParam> queryWebsiteParam(WebsiteParam websiteParam);

    Page<WebsiteParam> queryWebsiteParamByPage(WebsiteParam websiteParam, Page page);

    WebsiteParam queryWebsiteParamById(String paramId);

    WebsiteParam queryWebsiteParamByCode(String paramCode);
}
