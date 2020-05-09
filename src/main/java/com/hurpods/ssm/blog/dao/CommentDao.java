package com.hurpods.ssm.blog.dao;

import com.hurpods.ssm.blog.models.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

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

    void updateCommentUser(@Param(value = "commentAuthorNickName") String commentAuthorNickName,
                           @Param(value = "commentAuthorAvatar") String commentAuthorAvatar,
                           @Param(value = "commentAuthorId") Integer commentAuthorId);
}
