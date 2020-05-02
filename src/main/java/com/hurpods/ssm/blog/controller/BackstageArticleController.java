package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.Tag;
import com.hurpods.ssm.blog.service.ArticleService;
import com.hurpods.ssm.blog.service.TagService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public String writeArticle(HttpServletRequest req) {
        List<Tag> tagList = tagService.getAllTags();
        req.setAttribute("tagList", tagList);
        return "admin/article/write";
    }

    @RequestMapping("/saveArticle")
    @ResponseBody
    public String saveArticle(@RequestParam("articleTitle") String articleTitle,
                              @RequestParam("articleTagIds") Integer[] articleTagIds,
                              @RequestParam("htmlContent") String htmlContent,
                              @RequestParam("summary") String summary) {
        Article article = new Article();
        List<Tag> tagList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        article.setArticleTitle(articleTitle);
        article.setArticleAuthorId(0);
        article.setArticleContent(htmlContent);

        if (htmlContent.contains("<img")) {
            article.setHasPic(1);
        } else {
            article.setHasPic(0);
        }

        article.setArticleStatus(1);
        if (summary.length() < 122) {
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summary.substring(0, 122) + "... ...");
        }

        for (int i : articleTagIds) {
            tagList.add(tagService.getTagById(i));
        }

        article.setTagList(tagList);
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

    @RequestMapping("/editPage/{articleId}")
    public ModelAndView editPage(@PathVariable("articleId") Integer articleId,HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.getArticleById(articleId);
        List<Tag> tagList = articleService.getTagsByArticleId(articleId);
        List<Integer> tagIds = new ArrayList<>();
        for (Tag tag : tagList) {
            tagIds.add(tag.getTagId());
        }
        List<Tag> tagsList = tagService.getAllTags();
        req.setAttribute("tagList", tagsList);

        modelAndView.addObject("article", article);
        modelAndView.addObject("tagIds", tagIds);
        modelAndView.setViewName("admin/article/edit");

        return modelAndView;
    }

    @RequestMapping("/modifyArticle")
    @ResponseBody
    public String modifyArticle(@RequestParam("articleTitle") String articleTitle,
                                @RequestParam("articleTagIds") Integer[] articleTagIds,
                                @RequestParam("htmlContent") String htmlContent,
                                @RequestParam("summary") String summary,
                                @RequestParam("articleId") Integer articleId) {
        Article article = articleService.getArticleById(articleId);
        Timestamp nowTime = new Timestamp(new Date().getTime());
        List<Tag> tagList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        article.setArticleTitle(articleTitle);
        article.setArticleUpdateTime(nowTime);
        article.setArticleContent(htmlContent);

        if (htmlContent.contains("<img")) {
            article.setHasPic(1);
        } else {
            article.setHasPic(0);
        }

        article.setArticleStatus(1);
        if (summary.length() < 122) {
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summary.substring(0, 122) + "... ...");
        }

        for (int i : articleTagIds) {
            tagList.add(tagService.getTagById(i));
        }

        article.setTagList(tagList);

        try {
            System.out.println(article);
            map.put("status", "true");
            map.put("msg", "更新成功");
        } catch (Exception e) {
            map.put("status", "false");
            map.put("msg", e.getMessage());
        }

        return new JSONObject(map).toString();
    }

    @RequestMapping("/getAllArticle")
    public String getAllArticle(HttpServletRequest req) {
        List<Article> articleList = articleService.getAllArticle();
        Map<String, Integer> map = new HashMap<>();

        for (Article article : articleList) {
            article.setTagList(articleService.getTagsByArticleId(article.getArticleId()));
        }

        map.put("count", articleService.getArticleCount());
        map.put("view", articleService.getArticleView());
        map.put("comment", articleService.getArticleComment());

        req.setAttribute("articleList", articleList);
        req.setAttribute("status", map);

        return "admin/article/list";
    }

    @RequestMapping("/deleteArticle")
    public void deleteArticle(@RequestParam(value = "articleId") Integer articleId) {
        articleService.deleteById(articleId);
        articleService.deleteByArticleId(articleId);
    }
}
