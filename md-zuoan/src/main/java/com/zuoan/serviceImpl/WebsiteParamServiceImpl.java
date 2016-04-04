package com.zuoan.serviceImpl;

import com.zuoan.dao.WebsiteParamDao;
import com.zuoan.module.WebsiteParam;
import com.zuoan.service.WebsiteParamService;
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
public class WebsiteParamServiceImpl implements WebsiteParamService {
    @Resource
    private WebsiteParamDao websiteParamDao;
    @Override
    public boolean addWebsiteParam(WebsiteParam websiteParam) {
        if (websiteParam == null){
            return false;
        }
        return websiteParamDao.addWebsiteParam(websiteParam)>0;
    }
    @Override
    public boolean delWebsiteParam(String paramId) {
        if (StringUtils.isBlank(paramId)){
            return false;
        }
        if (websiteParamDao.selectWebsiteParamById(paramId)==null){
            return false;
        }
        return  websiteParamDao.delWebsiteParam(paramId)>0;
    }
    @Override
    public boolean updateWebsiteParam(WebsiteParam websiteParam) {
        if (websiteParam == null ||StringUtils.isBlank(websiteParam.getParamId())){
            return false;
        }
        if (websiteParamDao.selectWebsiteParamById(websiteParam.getParamId())==null){
            return false;
        }
       return websiteParamDao.updateWebsiteParam(websiteParam)>0;
    }
    @Override
    public List<WebsiteParam> queryWebsiteParam(WebsiteParam websiteParam) {
        return websiteParamDao.selectWebsiteParam(websiteParam);
    }

    @Override
    public Page<WebsiteParam> queryWebsiteParamByPage(WebsiteParam websiteParam, Page page) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        websiteParamDao.selectWebsiteParam(websiteParam);
        return PageHelper.endPage();
    }

    @Override
    public WebsiteParam queryWebsiteParamById(String paramId) {
        if (StringUtils.isBlank(paramId)){
            return null;
        }
        return websiteParamDao.selectWebsiteParamById(paramId);
    }

    @Override
    public WebsiteParam queryWebsiteParamByCode(String paramCode) {
        if (StringUtils.isBlank(paramCode)){
            return null;
        }
        return websiteParamDao.selectWebsiteParamById(paramCode);
    }
}
