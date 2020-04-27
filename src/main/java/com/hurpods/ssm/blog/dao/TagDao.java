package com.hurpods.ssm.blog.dao;

import com.hurpods.ssm.blog.models.Tag;

import java.util.List;

public interface TagDao {
    List<Tag>getAllTags();
    Tag getTagById(Integer tagId);
    void deleteTagById(Integer tagId);
    Tag createTag(Tag tag);
}
