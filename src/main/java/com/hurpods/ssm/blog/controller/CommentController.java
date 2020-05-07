package com.hurpods.ssm.blog.controller;

import cn.hutool.http.HtmlUtil;
import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.Comment;
import com.hurpods.ssm.blog.service.ArticleService;
import com.hurpods.ssm.blog.service.CommentService;
import com.hurpods.ssm.blog.service.UserService;
import com.hurpods.ssm.blog.utils.MyUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    private final MyUtil myUtil = new MyUtil();

    @RequestMapping(value = "/postComment", method = RequestMethod.POST)
    @ResponseBody
    public String postComment(HttpServletRequest req, Comment comment) {
        Timestamp nowTime = new Timestamp(new Date().getTime());
        Map<String, String> map = new HashMap<>();
        comment.setCommentTime(nowTime);
        comment.setCommentIp(myUtil.getIpAddress(req));

        //过滤XSS攻击
        comment.setCommentAuthorAvatar(HtmlUtil.escape(comment.getCommentAuthorAvatar()));
        comment.setCommentAuthorNickName(HtmlUtil.escape(comment.getCommentAuthorNickName()));
        try{
            comment.setCommentContent((URLDecoder.decode(comment.getCommentContent(),"UTF-8")));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        if (comment.getCommentPreId() != 0) {
            comment.setCommentPreNickName(HtmlUtil.escape(comment.getCommentPreNickName()));
        }

        try {
            commentService.postComment(comment);
            Article article = articleService.getArticleById(comment.getCommentArticleId());
            articleService.updateCommentCount(article.getArticleId());

            map.put("status", "true");
            map.put("msg", "评论发布成功");
        } catch (Exception e) {
            map.put("status", "false");
            map.put("msg", e.getMessage());
            System.out.println(e.getMessage());
        }
        return new JSONObject(map).toString();
    }
}
