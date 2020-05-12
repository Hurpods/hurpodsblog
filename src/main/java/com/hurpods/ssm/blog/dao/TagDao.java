package com.hurpods.ssm.blog.dao;

import com.hurpods.ssm.blog.models.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagDao {
    List<Tag> getAllTags();

    Tag getTagById(Integer tagId);

    List<Tag> batchGetTag(@Param(value = "tagIdList") List<Integer> tagIdList);

    void deleteTagById(Integer tagId);

    void createTag(Tag tag);

    void updateTag(Tag tag);

    void batchDeleteTag(@Param("tagIds")List<Integer>tagIds);
}
