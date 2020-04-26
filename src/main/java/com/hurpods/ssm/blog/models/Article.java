package com.hurpods.ssm.blog.models;

import java.io.Serializable;

public class Article implements Serializable {
    private static final long serialVersionUID = 2791917326900161857L;
    //文章ID
    private Integer articleId;
    //文章作者
    private String articleAuthorId;
    //文章标题
    private String articleTitle;
    //文章内容
    private String articleContent;
    //浏览数
    private Integer articleViewCount;
    //评论数
    private Integer articleCommentCount;
    //文章状态
    private Integer articleStatus;
    //创建时间
    private Integer articleCreateTime;
    //最后修改时间
    private Integer articleUpdateTime;
    //文章摘要
    private String articleSummary;
}
