package com.hurpods.ssm.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LocateController {

    @RequestMapping("/")
    public String index(){
        return "public/index";
    }
}
