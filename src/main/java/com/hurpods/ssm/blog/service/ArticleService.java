package com.hurpods.ssm.blog.service;

import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.ArticleTagRef;
import com.hurpods.ssm.blog.models.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticle();

    Article getArticleById(Integer articleId);

    void deleteById(@Param(value = "articleId") Integer articleId);

    void batchDelete(List<Integer> idList);

    void insertArticle(Article article);

    void updateArticle(Article article);

    Integer getArticleCount();

    Integer getArticleView();

    Integer getArticleComment();

    List<Article> getArticleByPage(@Param(value = "status") Integer status,
                                   @Param(value = "pageIndex") Integer pageIndex,
                                   @Param(value = "pageSize") Integer pageSize);

    Article getNextArticle(@Param(value = "articleId") Integer articleId);

    Article getPreArticle(@Param(value = "articleId") Integer articleId);

    void updateCommentCount(@Param(value = "articleId") Integer articleId);

    void deleteByArticleId(Integer articleId);

    List<Tag> getTagsByArticleId(Integer articleId);

}
