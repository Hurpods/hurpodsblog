package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.User;
import com.hurpods.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class BackStageUserController {
    @Autowired
    UserService userService;

    @RequestMapping("/userManager")
    public String getAllUser(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute(userList);
        return "/admin/user/list";
    }
}
