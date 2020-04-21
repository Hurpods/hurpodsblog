package com.hurpods.ssm.blog.models;

import lombok.Data;

import java.io.Serializable;
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 6436639512695674934L;
    //唯一标识
    private Integer commentId;
    //用户标识
    private Integer commentUserId;
    //评论文章
    private Integer commentArticleId;
    //评论者昵称
    private String commentAuthorNickName;
    //评论者头像
    private String commentAuthorAvatar;
    //评论内容
    private String commentContent;
    //评论时的IP
    private String commentIp;
    //评论时间
    private Integer commentTime;
    //层主标识
    private Integer commentPreId;
    //层主昵称
    private String commentPreNickName;
}
