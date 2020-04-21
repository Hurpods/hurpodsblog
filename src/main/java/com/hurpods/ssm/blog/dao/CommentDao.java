package com.hurpods.ssm.blog.dao;

import com.hurpods.ssm.blog.models.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
    List<Comment> getUserAllCommentsById(Integer id);

    List<Comment> getArticleAllCommentsById(Integer id);

    List<Comment> getAllComments();

    void deleteCommentById(Integer id);

    Integer getCommentsNumber();

    void postComment(Comment comment);

    void editComment(Comment comment);
}
