package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.User;
import com.hurpods.ssm.blog.service.CommentService;
import com.hurpods.ssm.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {
    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/login")
    public String login() {
        return "public/login";
    }

    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();
        HashMap<String, String> hashMap = new HashMap<>();
        String userToken = req.getParameter("token");
        String tokenType = req.getParameter("type");

        hashMap = userService.verifyCorrect(userToken, tokenType);
        tokenType = hashMap.get("type");
        boolean status = Boolean.parseBoolean(hashMap.get("status"));

        if (status) {
            User user = userService.getUserByOthers(userToken);
            if (user != null) {
                map.put("code", "1");
                map.put("msg", tokenType + "已被注册！");
            } else {
                map.put("code", "0");
                map.put("msg", "");
            }
        } else {
            map.put("code", "1");
            map.put("msg", hashMap.get("msg"));
        }
        return new JSONObject(map).toString();
    }
}
