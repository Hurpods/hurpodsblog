package com.hurpods.ssm.blog.service;

import com.hurpods.ssm.blog.models.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();

    Tag getTagById(Integer tagId);

    void deleteTagById(Integer tagId);

    Tag createTag(Tag tag);
}
