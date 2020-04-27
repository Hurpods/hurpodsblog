package com.hurpods.ssm.blog.models;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Tag implements Serializable {
    private static final long serialVersionUID = 837842193143447836L;
    private Integer tagId;
    private String tagName;

    private List<Article> articleList;
    private Integer articleCount;
}
