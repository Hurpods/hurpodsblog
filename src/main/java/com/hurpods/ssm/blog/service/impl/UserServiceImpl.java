package com.hurpods.ssm.blog.service.impl;

import com.hurpods.ssm.blog.dao.UserDao;
import com.hurpods.ssm.blog.models.User;
import com.hurpods.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByOthers(String param) {
        return userDao.getUserByOthers(param);
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    @Override
    public User registerUser(User user) {
        userDao.registerUser(user);
        return user;
    }

    @Override
    public void updateUserInfo(User user) {
        userDao.updateUserInfo(user);
    }

    @Override
    public HashMap<String, String> verifyCorrect(String token, String returnType) {
        HashMap<String, String> result = new HashMap<>();
        String msg = "";
        String status = "";
        switch (returnType) {
            case "userName":
                if (token.equals("")) {
                    msg = "请输入6-20位的用户名";
                    status = "false";
                    break;
                } else {
                    char first = token.charAt(0);
                    if (token.length() < 6 || token.length() > 20) {
                        msg = "用户名不得小于6位或大于20位";
                        status = "false";
                        break;
                    } else if (!(((int) first >= 65 && (int) first <= 90) || ((int) first >= 97 && (int) first <= 122))) {
                        msg = "用户名不合法";
                        status = "false";
                        break;
                    }
                }
                msg = "";
                status = "true";
                returnType = "用户名";
                break;
            case "userEmail":
                String pat = "[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}";
                Pattern pattern = Pattern.compile(pat);
                if (token.equals("")) {
                    msg = "请输入邮箱";
                    status = "false";
                    break;
                } else {
                    Matcher matcher = pattern.matcher(token);
                    boolean isEmail = matcher.matches();
                    if (!isEmail) {
                        msg = "邮箱格式错误";
                        status = "false";
                        break;
                    }
                }
                msg = "";
                status = "true";
                returnType = "邮箱";
                break;
            case "userTel":
                pat = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";
                pattern = Pattern.compile(pat);
                Matcher matcher = pattern.matcher(token);
                boolean isTel = matcher.matches();
                boolean isEmpty = token.length() == 0;
                if (token.length() != 11 && !isEmpty) {
                    msg = "手机号码位数错误";
                    status = "false";
                    break;
                } else if (!isTel && !isEmpty) {
                    msg = "手机号码格式错误";
                    status = "false";
                    break;
                }
                msg = "";
                status = "true";
                returnType = "手机号";
                break;
        }
        result.put("status", status);
        result.put("msg", msg);
        result.put("type", returnType);
        return result;
    }

    @Override
    public void updateUserPsw(User user) {

    }
}
