package com.hurpods.ssm.blog.service.impl;

import com.hurpods.ssm.blog.dao.CommentDao;
import com.hurpods.ssm.blog.models.Comment;
import com.hurpods.ssm.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Override
    public List<Comment> getUserAllCommentsById(Integer id) {
        return commentDao.getUserAllCommentsById(id);
    }

    @Override
    public List<Comment> getArticleAllCommentsById(Integer id) {
        return commentDao.getArticleAllCommentsById(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDao.getAllComments();
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentDao.deleteCommentById(id);
    }

    @Override
    public void batchDeleteComments(List<Integer> commentIds) {
        commentDao.batchDeleteComments(commentIds);
    }

    @Override
    public Integer getCommentsNumber() {
        return commentDao.getCommentsNumber();
    }

    @Override
    public void postComment(Comment comment) {
        commentDao.postComment(comment);
    }

    @Override
    public void updateCommentUser(String commentAuthorNickName, String commentAuthorAvatar, Integer commentAuthorId) {
        commentDao.updateCommentUser(commentAuthorNickName, commentAuthorAvatar, commentAuthorId);
    }
}
