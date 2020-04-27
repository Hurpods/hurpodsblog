package com.hurpods.ssm.blog.dao;

import com.hurpods.ssm.blog.models.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    List<Article> getAllArticle();

    void deleteById(@Param(value="articleId") Integer articleId);

    void batchDelete(List<Integer> idList);

    Article insertArticle(Article article);

    Article updateArticle(Article article);

    int getArticleCount();

    int getArticleView();

    List<Article> getArticleByPage(@Param(value = "status") Integer status,
                                   @Param(value = "pageIndex") Integer pageIndex,
                                   @Param(value = "pageSize") Integer pageSize);

    Article getNextArticle(@Param(value="articleId") Integer articleId);
    Article getPreArticle(@Param(value="articleId") Integer articleId);

    void updateCommentCount(@Param(value="articleId") Integer articleId);
}
