package com.hurpods.ssm.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LocateController {

    @RequestMapping("/")
    public String index(){
        return "public/index";
    }

    @RequestMapping("/404")
    public String notFound(@RequestParam(required = false)String message, Model model){
        model.addAttribute("message",message);
        return "error/404";
    }

    @RequestMapping("/500")
    public String serverError(@RequestParam(required = false)String message, Model model){
        model.addAttribute("message",message);
        return "error/500";
    }
}
