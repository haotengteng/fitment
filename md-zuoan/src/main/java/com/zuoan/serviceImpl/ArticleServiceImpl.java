package com.zuoan.serviceImpl;

import com.zuoan.dao.ArticleInfoDao;
import com.zuoan.module.ArticleInfo;
import com.zuoan.service.ArticleService;
import com.zuoan.utils.mybatis.Page;
import com.zuoan.utils.mybatis.PageHelper;
import com.zuoan.utils.redis.RedisCacheable;
import com.zuoan.utils.redis.RedisCacheableForDelete;
import com.zuoan.utils.redis.RedisCacheableForUpdate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/4/1.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    ArticleInfoDao articleInfoDao;
    @Override
    public boolean AddArticleInfo(ArticleInfo articleInfo) {
        if (articleInfo==null|| StringUtils.isBlank(articleInfo.getArticleId())){
            return false;
        }
        return articleInfoDao.addArticleInfo(articleInfo) > 0;
    }

    @Override
    @RedisCacheableForUpdate
    public boolean updateArticleInfo(ArticleInfo articleInfo) {
        if (articleInfo==null|| StringUtils.isBlank(articleInfo.getArticleId())){
            return false;
        }
        if (articleInfoDao.selectArticleInfoById(articleInfo.getArticleId()) == null){
            return false;
        }
        return articleInfoDao.updateArticleInfo(articleInfo)>0;
    }

    @Override
    @RedisCacheableForDelete
    public boolean deleteArticleInfo(String articleId) {
        if ( StringUtils.isBlank(articleId)){
            return false;
        }
        if (articleInfoDao.selectArticleInfoById(articleId) == null){
            return false;
        }
        return articleInfoDao.deleteArticleInfo(articleId) >0;
    }

    @Override
    @RedisCacheable
    public ArticleInfo queryArticleInfoById(String articleId) {
        if ( StringUtils.isBlank(articleId)){
            return null;
        }
        return articleInfoDao.selectArticleInfoById(articleId);
    }

    @Override
    public Page<ArticleInfo> queryArticleInfoByPage(ArticleInfo articleInfo , Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        articleInfoDao.selectArticleInfo(articleInfo);
        return PageHelper.endPage();
    }
}
