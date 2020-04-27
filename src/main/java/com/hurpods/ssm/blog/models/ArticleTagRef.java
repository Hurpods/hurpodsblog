package com.hurpods.ssm.blog.models;

import java.io.Serializable;
//多对多级联中间表
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = 675615389154231586L;
    private Integer articleTagRefId;
    private Integer articleId;
    private Integer tagId;
}
