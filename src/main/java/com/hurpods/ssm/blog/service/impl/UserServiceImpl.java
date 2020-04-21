package com.hurpods.ssm.blog.service.impl;

import com.hurpods.ssm.blog.dao.UserDao;
import com.hurpods.ssm.blog.models.User;
import com.hurpods.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public User getUserByOthers(String param) {
        return userDao.getUserByOthers(param);
    }

    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    public User registerUser(User user) {
        userDao.registerUser(user);
        return user;
    }

    public void updateUserInfo(User user) {
        userDao.updateUserInfo(user);
    }
}
