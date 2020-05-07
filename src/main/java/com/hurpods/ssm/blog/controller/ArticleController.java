package com.hurpods.ssm.blog.controller;

import cn.hutool.http.HtmlUtil;
import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.Comment;
import com.hurpods.ssm.blog.models.User;
import com.hurpods.ssm.blog.service.ArticleService;
import com.hurpods.ssm.blog.service.CommentService;
import com.hurpods.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/{articleId}")
    public String getArticleDetail(@PathVariable("articleId") Integer articleId, Model model) {
        Article article = articleService.getArticleById(articleId);
        if (article == null) {
            return "error/404";
        }
        model.addAttribute("article", article);
        User author = userService.getUserById(article.getArticleAuthorId());
        model.addAttribute("author", author);

        Article preArticle = articleService.getPreArticle(articleId);
        Article nextArticle = articleService.getNextArticle(articleId);
        model.addAttribute("pre", preArticle);
        model.addAttribute("next", nextArticle);

        List<Comment> commentList = commentService.getArticleAllCommentsById(articleId);
//        for (Comment comment : commentList) {
//            comment.setCommentContent(HtmlUtil.unescape(comment.getCommentContent()));
//        }
        model.addAttribute("commentList", commentList);

        return "public/articleDetail";
    }
}
