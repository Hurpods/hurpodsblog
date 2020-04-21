package com.hurpods.ssm.blog.models;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8119893934996632553L;
    //唯一标识
    private Integer userId;
    //用户名
    private String userName;
    //用户密码（hash）
    private String userPsw;
    //用户昵称
    private String userNickName;
    //用户邮箱
    private String userEmail;
    //用户手机
    private String userTel;
    //用户头像
    private String userAvatar;
    //用户最后登录的IP
    private String userLastLoginIp;
    //用户注册时间
    private Integer userRegisterTime;
    //用户最后登录时间
    private Integer userLastLoginTime;
    //地理位置
    private String province;
    private String city;
}
