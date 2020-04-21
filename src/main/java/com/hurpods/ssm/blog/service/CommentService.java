package com.hurpods.ssm.blog.service;

import com.hurpods.ssm.blog.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getUserAllCommentsById(Integer id);

    List<Comment> getArticleAllCommentsById(Integer id);

    List<Comment> getAllComments();

    void deleteCommentById(Integer id);

    Integer getCommentsNumber();

    void postComment(Comment comment);

    void editComment(Comment comment);
}
