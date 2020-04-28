package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.Tag;
import com.hurpods.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/admin/article")
public class BackstageArticleController {
    @Autowired
    TagService tagService;

    @RequestMapping("/write")
    public String writeArticle(Model model) {
        List<Tag> tagList = tagService.getAllTags();
        model.addAttribute("tagList", tagList);
        return "admin/article/write";
    }
}
