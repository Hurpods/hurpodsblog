package com.hurpods.ssm.blog.models;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 2791917326900161857L;
    //文章ID
    private Integer articleId;
    //文章作者
    private Integer articleAuthorId;
    //文章标题
    private String articleTitle;
    //文章内容
    private String articleContent;
    //浏览数
    private Integer articleViewCount;
    //评论数
    private Integer articleCommentsCount;
    //文章状态
    private Integer articleStatus;
    //创建时间
    private Timestamp articleCreateTime;
    //最后修改时间
    private Timestamp articleUpdateTime;
    //文章摘要
    private String articleSummary;
    //是否有图片
    private Integer hasPic;
    //第一张图片地址
    private String firstPicUrl;
    //是否是error类文章
    private Integer isError;
    //以下非数据库字段

    //多对多级联
    private List<Tag> tagList;
}
