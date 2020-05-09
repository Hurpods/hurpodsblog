package com.hurpods.ssm.blog.service;

import com.hurpods.ssm.blog.models.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
    List<Comment> getUserAllCommentsById(Integer id);

    List<Comment> getArticleAllCommentsById(Integer id);

    List<Comment> getAllComments();

    void deleteCommentById(Integer id);

    Integer getCommentsNumber();

    void postComment(Comment comment);

    void editComment(Comment comment);

    void updateCommentUser(@Param(value = "commentAuthorNickName") String commentAuthorNickName,
                           @Param(value = "commentAuthorAvatar") String commentAuthorAvatar,
                           @Param(value = "commentAuthorId") Integer commentAuthorId);
}
