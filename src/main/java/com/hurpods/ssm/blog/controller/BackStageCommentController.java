package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.Comment;
import com.hurpods.ssm.blog.service.ArticleService;
import com.hurpods.ssm.blog.service.CommentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/comment")
public class BackStageCommentController {
    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/getAllComment")
    public String getAllComment(Model model) {
        List<Comment> commentList = commentService.getAllComments();

        List<Integer> articleIdList = commentList
                .stream().map(Comment::getCommentArticleId).collect(Collectors.toList());

        List<Article> articleList=articleService.getArticleByIdList(articleIdList);

        model.addAttribute(commentList);
        model.addAttribute(articleList);
        return "/admin/comment/list";
    }

    @RequestMapping("/deleteComment/{commentId}")
    @ResponseBody
    public String deleteComment(@PathVariable("commentId")Integer commentId){
        Map<String,String>map=new HashMap<>();
        try {
            commentService.deleteCommentById(commentId);
            map.put("status", "true");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("status", "false");
            System.out.println(e.getMessage());
            map.put("msg", e.getMessage());
        }
        return new JSONObject(map).toString();
    }

    @RequestMapping("/batchDeleteComments")
    @ResponseBody
    public String batchDeleteComments(@RequestParam("commentIds")List<Integer> commentIds){
        Map<String, String> map = new HashMap<>();
        try {
            commentService.batchDeleteComments(commentIds);
            map.put("status", "true");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("status", "false");
            map.put("msg", e.getMessage());
        }

        return new JSONObject(map).toString();
    }
}
