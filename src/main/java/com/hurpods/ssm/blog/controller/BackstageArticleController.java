package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.Tag;
import com.hurpods.ssm.blog.service.ArticleService;
import com.hurpods.ssm.blog.service.TagService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/admin/article")
public class BackstageArticleController {
    @Autowired
    TagService tagService;

    @Autowired
    ArticleService articleService;

    @RequestMapping("/write")
    public String writeArticle(Model model) {
        List<Tag> tagList = tagService.getAllTags();
        model.addAttribute("tagList", tagList);
        return "admin/article/write";
    }

    @RequestMapping("/saveArticle")
    @ResponseBody
    public String saveArticle(@RequestParam("articleTitle") String articleTitle,
                              @RequestParam("articleTagIds") Integer[] articleTagIds,
                              @RequestParam("htmlContent") String htmlContent,
                              @RequestParam("summary") String summary) {
        Article article = new Article();
        Timestamp nowTime = new Timestamp(new Date().getTime());
        List<Tag> tagList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();


        article.setArticleTitle(articleTitle);
        article.setArticleAuthorId(0);
        article.setArticleContent(htmlContent);
        article.setArticleCreateTime(nowTime);
        article.setArticleUpdateTime(nowTime);
        if (htmlContent.contains("<img")) {
            article.setHasPic(1);
        } else {
            article.setHasPic(0);
        }
        for (int i : articleTagIds) {
            tagList.add(tagService.getTagById(i));
        }
        article.setTagList(tagList);
        article.setArticleStatus(1);
        article.setArticleSummary(summary.substring(0, 122) + "... ...");

        try {
            articleService.insertArticle(article);
            System.out.println(article.toString());
            map.put("status", "true");
            map.put("msg", "文章发表成功");
        } catch (Exception e) {
            map.put("status", "false");
            map.put("msg", e.getMessage());
        }

        return new JSONObject(map).toString();
    }

    @RequestMapping("/modifyArticle")
    @ResponseBody
    public String modifyArticle() {
        return "";
    }

    @RequestMapping("/getAllArticle")
    public String getAllArticle(HttpServletRequest req) {
        List<Article> articleList = articleService.getAllArticle();
        req.setAttribute("articleList", articleList);

        Map<String, Integer> map = new HashMap<>();

        map.put("count", articleService.getArticleCount());
        map.put("view", articleService.getArticleView());
        map.put("comment", articleService.getArticleComment());
        req.setAttribute("status", map);

        return "admin/article/list";
    }
}
