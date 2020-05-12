package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.Tag;
import com.hurpods.ssm.blog.service.ArticleService;
import com.hurpods.ssm.blog.service.CommentService;
import com.hurpods.ssm.blog.service.TagService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

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

    @Autowired
    CommentService commentService;

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
        Map<String, String> map = new HashMap<>();
        Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");
        Matcher matcher = p.matcher(htmlContent);
        String firstPicUrl;
        List<Tag> tagList = tagService.batchGetTag(Arrays.asList(articleTagIds));

        article.setArticleTitle(articleTitle);
        article.setArticleAuthorId(1);
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
        } else {
            article.setHasPic(0);

            for (Tag tag : tagList) {
                if (tag.getTagName().equalsIgnoreCase("error")) {
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
        List<Integer> tagIds = new ArrayList<>();
        for (Tag tag : article.getTagList()) {
            tagIds.add(tag.getTagId());
        }
        List<Tag> tagsList = tagService.getAllTags();

        modelAndView.addObject("tagList", tagsList);
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
        Map<String, String> map = new HashMap<>();
        Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");
        Matcher matcher = p.matcher(htmlContent);
        String firstPicUrl;
        List<Tag> tagList = tagService.batchGetTag(Arrays.asList(articleTagIds));

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

        } else {
            article.setHasPic(0);

            for (Tag tag : tagList) {
                if (tag.getTagName().equalsIgnoreCase("error")) {
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
            System.out.println(e.getMessage());
            map.put("msg", e.getMessage());
        }
        return new JSONObject(map).toString();
    }

    @RequestMapping("/getAllArticle")
    public String getAllArticle(HttpServletRequest req) {
        List<Article> articleList = articleService.getAllArticle();
        Map<String, Integer> map = new HashMap<>();

        map.put("count", articleService.getArticleCount());
        map.put("view", articleService.getArticleView());
        map.put("comment", articleService.getArticleComment());

        req.setAttribute("articleList", articleList);
        req.setAttribute("status", map);

        return "admin/article/list";
    }

    @RequestMapping("/deleteArticle")
    @ResponseBody
    public String deleteArticle(@RequestParam(value = "articleId") Integer articleId) {
        Map<String, String> map = new HashMap<>();
        try {
            articleService.deleteById(articleId);
            map.put("status", "true");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("status", "false");
            map.put("msg", e.getMessage());
        }
        return new JSONObject(map).toString();
    }

    @RequestMapping("/tags")
    public String getAllTags(Model model) {
        List<Tag> tagList = tagService.getAllTags();
        model.addAttribute(tagList);
        return "admin/article/tags";
    }

    @RequestMapping("/deleteTag/{tagId}")
    @ResponseBody
    public String deleteTag(@PathVariable("tagId") Integer tagId) {
        Map<String, String> map = new HashMap<>();
        try {
            tagService.deleteTagById(tagId);
            map.put("status", "true");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("status", "false");
            map.put("msg", e.getMessage());
        }
        return new JSONObject(map).toString();
    }

    @RequestMapping("/updateTag")
    @ResponseBody
    public String updateTag(@RequestParam("tagId") Integer tagId, @RequestParam("newTagName") String newTagName) {
        Map<String, String> map = new HashMap<>();
        Tag tag = tagService.getTagById(tagId);
        newTagName = HtmlUtils.htmlEscape(newTagName);
        tag.setTagName(newTagName);
        try {
            tagService.updateTag(tag);
            map.put("status", "true");
            map.put("msg", "修改成功");
        } catch (Exception e) {
            map.put("status", "false");
            map.put("msg", e.getMessage());
        }
        return new JSONObject(map).toString();
    }

    @RequestMapping("/createTag")
    @ResponseBody
    public String createTag(@RequestParam("tagName") String tagName) {
        Map<String, String> map = new HashMap<>();
        Tag tag = new Tag();
        tagName = HtmlUtils.htmlEscape(tagName);
        tag.setTagName(tagName);

        try {
            tagService.createTag(tag);
            map.put("status", "true");
            map.put("msg", "添加成功");
        } catch (Exception e) {
            map.put("status", "false");
            map.put("msg", e.getMessage());
        }

        return new JSONObject(map).toString();
    }

    @RequestMapping("/batchDeleteTags")
    @ResponseBody
    public String batchDeleteTags(@RequestParam("tagIds") List<Integer> tagIds) {
        Map<String, String> map = new HashMap<>();
        try {
            tagService.batchDeleteTag(tagIds);
            map.put("status", "true");
            map.put("msg", "删除成功");
        } catch (Exception e) {
            map.put("status", "false");
            map.put("msg", e.getMessage());
        }

        return new JSONObject(map).toString();
    }
}
