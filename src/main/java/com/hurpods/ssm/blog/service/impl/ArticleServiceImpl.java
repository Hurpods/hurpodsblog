package com.hurpods.ssm.blog.service.impl;

import com.hurpods.ssm.blog.dao.ArticleDao;
import com.hurpods.ssm.blog.dao.ArticleTagRefDao;
import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.ArticleTagRef;
import com.hurpods.ssm.blog.models.Tag;
import com.hurpods.ssm.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Autowired
    ArticleTagRefDao atrDao;

    @Override
    public List<Article> getAllArticle() {
        return articleDao.getAllArticle();
    }

    @Override
    public Article getArticleById(Integer articleId) {
        return articleDao.getArticleById(articleId);
    }

    @Override
    public void deleteById(Integer articleId) {
        articleDao.deleteById(articleId);
    }

    @Override
    public void batchDelete(List<Integer> idList) {
        articleDao.batchDelete(idList);
    }

    @Override
    public void insertArticle(Article article) {
        Timestamp nowTime = new Timestamp(new Date().getTime());
        article.setArticleCreateTime(nowTime);
        article.setArticleUpdateTime(nowTime);

        articleDao.insertArticle(article);
        for (Tag tag : article.getTagList()) {
            ArticleTagRef atr = new ArticleTagRef(article.getArticleId(), tag.getTagId());
            atrDao.createArticleTagRef(atr);
        }
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
        atrDao.deleteByArticleId(article.getArticleId());

        for (Tag tag : article.getTagList()) {
            ArticleTagRef atr = new ArticleTagRef(article.getArticleId(), tag.getTagId());
            atrDao.createArticleTagRef(atr);
        }
    }

    @Override
    public Integer getArticleCount() {
        return articleDao.getArticleCount();
    }

    @Override
    public Integer getArticleView() {
        return articleDao.getArticleView();
    }

    @Override
    public Integer getArticleComment() {
        return articleDao.getArticleComment();
    }

    @Override
    public List<Article> getArticleByPage(Integer pageIndex, Integer pageSize) {
        return articleDao.getArticleByPage(pageIndex, pageSize);
    }

    @Override
    public Article getNextArticle(Integer articleId) {
        return articleDao.getNextArticle(articleId);
    }

    @Override
    public Article getPreArticle(Integer articleId) {
        return articleDao.getPreArticle(articleId);
    }

    @Override
    public void updateCommentCount(Integer articleId) {
        articleDao.updateCommentCount(articleId);
    }

    @Override
    public void deleteByArticleId(Integer articleId) {
        atrDao.deleteByArticleId(articleId);
    }

    @Override
    public List<Tag> getTagsByArticleId(Integer articleId) {
        return atrDao.getTagsByArticleId(articleId);
    }
}
