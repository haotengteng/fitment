package com.zuoan.service;

import com.zuoan.module.ArticleInfo;
import com.zuoan.utils.mybatis.Page;

/**
 * Created by Administrator on 2016/4/1.
 */
public interface ArticleService {
    boolean AddArticleInfo(ArticleInfo articleInfo);

    boolean updateArticleInfo(ArticleInfo articleInfo);

    boolean deleteArticleInfo(String articleId);

    ArticleInfo queryArticleInfoById(String articleId);

    Page<ArticleInfo> queryArticleInfoByPage(ArticleInfo articleInfo ,Page page);

}
