package com.zuoan.dao;

import com.zuoan.module.ArticleInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
public interface ArticleInfoDao {
    int addArticleInfo(ArticleInfo articleInfo);

    int updateArticleInfo(ArticleInfo articleInfo);

    int deleteArticleInfo(String articleId);

    ArticleInfo selectArticleInfoById(String articleId);

    List<ArticleInfo> selectArticleInfo(ArticleInfo articleInfo);
}
