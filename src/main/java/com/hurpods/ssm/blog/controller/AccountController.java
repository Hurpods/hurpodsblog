package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.models.User;
import com.hurpods.ssm.blog.service.CityService;
import com.hurpods.ssm.blog.service.CommentService;
import com.hurpods.ssm.blog.service.ProvinceService;
import com.hurpods.ssm.blog.service.UserService;
import com.hurpods.ssm.blog.utils.MyUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {
    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    ProvinceService provinceService;

    @Autowired
    CityService cityService;

    private final MyUtil myUtil = new MyUtil();
    private static final String DEFAULT_AVATAR = "/img/avatar/0.png";

    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    @ResponseBody
    public String checkToken(HttpServletRequest req) {
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
                map.put("status", "false");
                map.put("msg", "该" + tokenType + "已被注册！");
            } else {
                map.put("status", "true");
                map.put("msg", "");
            }
        } else {
            map.put("status", "false");
            map.put("msg", hashMap.get("msg"));
        }
        return new JSONObject(map).toString();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public void registerUser(HttpServletRequest req) {
        Timestamp nowTime = new Timestamp(new Date().getTime());
        User user = new User();

        String name = req.getParameter("username");
        String psw = req.getParameter("password");
        String tel = req.getParameter("telnumber");
        String email = req.getParameter("email");

        user.setUserName(name);
        psw = myUtil.hashPass(psw, name);
        user.setUserPsw(psw);
        if (!tel.equals("")) {
            user.setUserTel(tel);
        }
        user.setUserEmail(email);
        String defaultNickName = "用户" + myUtil.getRandomString(8);
        user.setUserNickName(defaultNickName);
        user.setUserAvatar(DEFAULT_AVATAR);
        user.setUserRegisterTime(nowTime);
        user.setUserLastLoginTime(nowTime);
        user.setUserLastLoginIp(myUtil.getIpAddress(req));

        userService.registerUser(user);

        req.getSession().setAttribute("user", user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest req) {
        String token = req.getParameter("token");
        String psw = req.getParameter("password");
        String msg;
        Map<String, String> map = new HashMap<>();
        User user = userService.getUserByOthers(token);

        if (user != null) {
            String name = user.getUserName();
            psw = myUtil.hashPass(psw, name);
            if (psw.equals(user.getUserPsw())) {
                map.put("status", "true");
                msg = "";
                req.getSession().setAttribute("user", user);
            } else {
                map.put("status", "false");
                msg = "账号或密码错误";
            }
        } else {
            map.put("status", "false");
            msg = "该账号未注册";
        }
        map.put("msg", msg);
        return new JSONObject(map).toString();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public String updateUser(HttpServletRequest req) {
        String nickName = req.getParameter("update_nickname");
        String provinceCode = req.getParameter("province_code");
        String province = provinceService.getProvinceByCode(provinceCode).getProvinceName();
        String cityCode = req.getParameter("city_code");
        String city = cityService.getCityByCode(cityCode).getCityName();
        String email = req.getParameter("update_email");
        String tel = req.getParameter("update_tel");

        User user = (User) req.getSession().getAttribute("user");

        if (!nickName.equals("")) {
            user.setUserNickName(nickName);
        }
        if (!email.equals("")) {
            user.setUserEmail(email);
        }
        if (!tel.equals("")) {
            user.setUserTel(tel);
        }
        if (!province.equals("")) {
            user.setProvince(province);
        }
        if (!city.equals("")) {
            user.setCity(city);
        }

        userService.updateUserInfo(user);

        logout(req.getSession());
        req.getSession().setAttribute("user", user);

        return "redirect:/profile";
    }

    @RequestMapping(value = "/updatePsw", method = RequestMethod.POST)
    @ResponseBody
    public String updatePsw(@RequestParam(value = "oldPassword") String oldPassword,
                            @RequestParam(value = "newPassword") String newPassword, HttpServletRequest req) {

        Map<String, String> map = new HashMap<>();
        String msg;
        if (oldPassword.equals("") || newPassword.equals("")) {
            map.put("status", "false");
            msg = "新密码或原始密码为空";
        } else if (oldPassword.equals(newPassword)) {
            map.put("status", "false");
            msg = "新密码不得与原始密码相同";
        } else if (newPassword.length() < 6) {
            map.put("status", "false");
            msg = "新密码位数不得小于6位";
        } else {
            User user = (User) req.getSession().getAttribute("user");
            if (user.getUserPsw().equals(myUtil.hashPass(oldPassword, user.getUserName()))) {
                user.setUserPsw(myUtil.hashPass(newPassword, user.getUserName()));
                userService.updateUserPsw(user);
                map.put("status", "true");
                msg = "";

                logout(req.getSession());
            } else {
                map.put("status", "false");
                msg = "原始密码错误";
            }
        }
        map.put("msg", msg);
        return new JSONObject(map).toString();
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestParam(value = "password") String password, HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();
        String msg;
        User user = (User) req.getSession().getAttribute("user");
        if (user.getUserPsw().equals(myUtil.hashPass(password, user.getUserName()))) {
            userService.deleteUserById(user.getUserId());
            if (!user.getUserAvatar().equals(DEFAULT_AVATAR)) {
                File file = new File(req.getContextPath() + user.getUserAvatar());
                file.delete();
            }
            map.put("status", "true");
            msg = "删除账号成功，3秒后返回首页";
            logout(req.getSession());
        } else {
            map.put("status", "false");
            msg = "密码错误，请重新输入";
        }
        map.put("msg", msg);
        return new JSONObject(map).toString();
    }
}
