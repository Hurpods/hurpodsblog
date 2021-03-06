package com.hurpods.ssm.blog.service;

import com.hurpods.ssm.blog.models.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User getUserById(Integer id);

    User getUserByOthers(String param);

    void deleteUserById(Integer id);

    User registerUser(User user);

    void updateUserInfo(User user);

    HashMap<String, String> verifyCorrect(String token, String type);

    void updateUserPsw(User user);
}
