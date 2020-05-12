package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.Comment;
import com.hurpods.ssm.blog.models.Tag;
import com.hurpods.ssm.blog.models.User;
import com.hurpods.ssm.blog.service.ArticleService;
import com.hurpods.ssm.blog.service.CommentService;
import com.hurpods.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LocateController {
    @Autowired
    ArticleService articleService;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Article> articleList = articleService.getArticleByPage(0,10);
        model.addAttribute("articleList", articleList);
        return "public/home";
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "public/login";
    }

    @RequestMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Comment> commentList = commentService.getUserAllCommentsById(user.getUserId());
        model.addAttribute("commentList", commentList);
        return "account/profile";
    }

    @RequestMapping("/update")
    public String update() {
        return "account/update";
    }

    @RequestMapping("/admin")
    public String backstage() {
        return "redirect:/admin/article/getAllArticle";
    }
}
