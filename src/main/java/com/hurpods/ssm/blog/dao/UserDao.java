package com.hurpods.ssm.blog.dao;

import com.hurpods.ssm.blog.models.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<User>getAllUser();
    User getUserById(Integer id);
    User getUserByOthers(String param);
    void deleteUserById(Integer id);
    void registerUser(User user);
    void updateUserInfo(User user);
    void updateUserPsw(User user);
}
