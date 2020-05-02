package com.hurpods.ssm.blog.dao;

import com.hurpods.ssm.blog.models.ArticleTagRef;
import com.hurpods.ssm.blog.models.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleTagRefDao {
    void createArticleTagRef(ArticleTagRef articleTagRef);

    void deleteByArticleId(@Param(value = "articleId") Integer articleId);

    List<Tag> getTagsByArticleId(@Param(value = "articleId") Integer articleId);

    List<Tag> getTagsByTagId(@Param(value = "tagId") Integer tagId);
}
