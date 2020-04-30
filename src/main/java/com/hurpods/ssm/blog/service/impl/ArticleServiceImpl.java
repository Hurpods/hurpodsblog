package com.hurpods.ssm.blog.service.impl;

import com.hurpods.ssm.blog.dao.ArticleDao;
import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;

    @Override
    public List<Article> getAllArticle() {
        return articleDao.getAllArticle();
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
    public Article insertArticle(Article article) {
        articleDao.insertArticle(article);
        return article;
    }

    @Override
    public Article updateArticle(Article article) {
        articleDao.updateArticle(article);
        return article;
    }

    @Override
    public int getArticleCount() {
        return articleDao.getArticleCount();
    }

    @Override
    public int getArticleView() {
        return articleDao.getArticleView();
    }

    @Override
    public List<Article> getArticleByPage(Integer status, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public Article getNextArticle(Integer articleId) {
        return null;
    }

    @Override
    public Article getPreArticle(Integer articleId) {
        return null;
    }

    @Override
    public void updateCommentCount(Integer articleId) {

    }
}
