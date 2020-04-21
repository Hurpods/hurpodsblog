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

    public List<Comment> getUserAllCommentsById(Integer id) {
        return commentDao.getUserAllCommentsById(id);
    }

    public List<Comment> getArticleAllCommentsById(Integer id) {
        return commentDao.getArticleAllCommentsById(id);
    }

    public List<Comment> getAllComments() {
        return commentDao.getAllComments();
    }

    public void deleteCommentById(Integer id) {
        commentDao.deleteCommentById(id);
    }

    public Integer getCommentsNumber() {
        return commentDao.getCommentsNumber();
    }

    public void postComment(Comment comment) {
        commentDao.postComment(comment);
    }

    public void editComment(Comment comment) {
        commentDao.editComment(comment);
    }
}
