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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");
        Matcher matcher = p.matcher(htmlContent);
        String firstPicUrl;

        article.setArticleTitle(articleTitle);
        article.setArticleAuthorId(0);
        article.setArticleContent(htmlContent);
        article.setIsError(0);

        if (matcher.find()) {
            article.setHasPic(1);
            String group = matcher.group(2);
            Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
            Matcher matcher2 = srcText.matcher(group);
            if (matcher2.find()) {
                firstPicUrl = matcher2.group(3);
                article.setFirstPicUrl(firstPicUrl);
            }

            for (int i : articleTagIds) {
                tagList.add(tagService.getTagById(i));
            }
        } else {
            article.setHasPic(0);

            for (int i : articleTagIds) {
                Tag temp = tagService.getTagById(i);
                tagList.add(temp);
                if (temp.getTagName().equals("error")) {
                    article.setIsError(1);
                }
            }
        }

        article.setArticleStatus(1);
        if (summary.length() < 122) {
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summary.substring(0, 122) + "... ...");
        }

        article.setTagList(tagList);
        try {
            articleService.insertArticle(article);
            map.put("status", "true");
            map.put("msg", "文章发表成功");
        } catch (Exception e) {
            map.put("status", "false");
            map.put("msg", e.getMessage());
        }

        return new JSONObject(map).toString();
    }

    @RequestMapping("/editPage/{articleId}")
    public ModelAndView editPage(@PathVariable("articleId") Integer articleId, HttpServletRequest req) {
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
        Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");
        Matcher matcher = p.matcher(htmlContent);
        String firstPicUrl;

        article.setArticleTitle(articleTitle);
        article.setArticleUpdateTime(nowTime);
        article.setArticleContent(htmlContent);
        article.setIsError(0);

        if (matcher.find()) {
            article.setHasPic(1);
            String group = matcher.group(2);
            Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
            Matcher matcher2 = srcText.matcher(group);
            if (matcher2.find()) {
                firstPicUrl = matcher2.group(3);
                article.setFirstPicUrl(firstPicUrl);
            }

            for (int i : articleTagIds) {
                tagList.add(tagService.getTagById(i));
            }
        } else {
            article.setHasPic(0);

            for (int i : articleTagIds) {
                Tag temp = tagService.getTagById(i);
                tagList.add(temp);
                if (temp.getTagName().equals("error")) {
                    article.setIsError(1);
                }
            }
        }

        article.setArticleStatus(1);
        if (summary.length() < 122) {
            article.setArticleSummary(summary);
        } else {
            article.setArticleSummary(summary.substring(0, 122) + "... ...");
        }

        article.setTagList(tagList);

        try {
            articleService.updateArticle(article);
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
