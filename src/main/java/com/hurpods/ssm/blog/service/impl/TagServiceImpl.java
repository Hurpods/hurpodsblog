package com.hurpods.ssm.blog.service.impl;

import com.hurpods.ssm.blog.dao.ArticleTagRefDao;
import com.hurpods.ssm.blog.dao.TagDao;
import com.hurpods.ssm.blog.models.Tag;
import com.hurpods.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    @Autowired
    ArticleTagRefDao articleTagRefDao;

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    @Override
    public Tag getTagById(Integer tagId) {
        return tagDao.getTagById(tagId);
    }

    @Override
    public List<Tag> batchGetTag(List<Integer> tagIdList) {
        return tagDao.batchGetTag(tagIdList);
    }

    @Override
    public void deleteTagById(Integer tagId) {
        tagDao.deleteTagById(tagId);
        articleTagRefDao.deleteByTagId(tagId);
    }

    @Override
    public void createTag(Tag tag) {
        tagDao.createTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        tagDao.updateTag(tag);
    }

    @Override
    public void batchDeleteTag(List<Integer> tagIds) {
        tagDao.batchDeleteTag(tagIds);
        articleTagRefDao.batchDeleteByTagId(tagIds);
    }
}
