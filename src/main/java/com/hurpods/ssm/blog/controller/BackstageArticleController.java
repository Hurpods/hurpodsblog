package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.Article;
import com.hurpods.ssm.blog.models.ArticleTagRef;
import com.hurpods.ssm.blog.models.Tag;
import com.hurpods.ssm.blog.service.ArticleService;
import com.hurpods.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                              @RequestParam("summary")String summary)
    {
        Article article=new Article();
        Timestamp nowTime = new Timestamp(new Date().getTime());
        List<Tag> tagList=new ArrayList<>();
        article.setArticleTitle(articleTitle);
        article.setArticleAuthorId(0);
        article.setArticleContent(htmlContent);
        article.setArticleCreateTime(nowTime);
        article.setArticleUpdateTime(nowTime);
        if(htmlContent.contains("<img")){
            article.setHasPic(1);
        }else {
            article.setHasPic(0);
        }
        for(int i:articleTagIds){
            tagList.add(tagService.getTagById(i));
        }
        article.setTagList(tagList);
        article.setArticleStatus(1);
        article.setArticleSummary(summary);
        System.out.println(article.toString());
        return "";
    }
}
