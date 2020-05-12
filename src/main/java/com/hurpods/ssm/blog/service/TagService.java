package com.hurpods.ssm.blog.service;

import com.hurpods.ssm.blog.models.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();

    Tag getTagById(Integer tagId);

    List<Tag> batchGetTag(List<Integer> tagIdList);

    void deleteTagById(Integer tagId);

    void createTag(Tag tag);

    void updateTag(Tag tag);

    void batchDeleteTag(List<Integer> tagIds);
}
