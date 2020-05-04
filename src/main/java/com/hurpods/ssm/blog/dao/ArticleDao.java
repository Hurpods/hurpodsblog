package com.hurpods.ssm.blog.dao;

import com.hurpods.ssm.blog.models.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    List<Article> getAllArticle();

    Article getArticleById(Integer articleId);

    void deleteById(@Param(value = "articleId") Integer articleId);

    void batchDelete(@Param(value="idList") List<Integer> idList);

    void insertArticle(Article article);

    void updateArticle(Article article);

    Integer getArticleCount();

    Integer getArticleView();

    Integer getArticleComment();

    List<Article> getArticleByPage(@Param(value = "pageIndex") Integer pageIndex,
                                   @Param(value = "pageSize") Integer pageSize);

    Article getNextArticle(@Param(value = "articleId") Integer articleId);

    Article getPreArticle(@Param(value = "articleId") Integer articleId);

    void updateCommentCount(@Param(value = "articleId") Integer articleId);
}
