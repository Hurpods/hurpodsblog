package com.hurpods.ssm.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocateController {

    @RequestMapping("/")
    public String index() {
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

    @RequestMapping("/edit")
    public String edit() {
        return "account/edit";
    }
}
