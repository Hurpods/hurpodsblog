package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.Tag;
import com.hurpods.ssm.blog.service.ArticleService;
import com.hurpods.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LocateController {
    @Autowired
    ArticleService articleService;
    @Autowired
    TagService tagService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Article> articleList = articleService.getArticleByPage(0, 10);
        for (Article article : articleList) {
            article.setTagList(articleService.getTagsByArticleId(article.getArticleId()));
        }
        model.addAttribute("articleList", articleList);
        return "public/home";
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "public/login";
    }

    @RequestMapping("/profile")
    public String profile() {
        return "account/profile";
    }

    @RequestMapping("/update")
    public String update() {
        return "account/update";
    }

    @RequestMapping("/admin")
    public String backstage() {
        return "admin/backstage";
    }
}
