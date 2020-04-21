package com.hurpods.ssm.blog.dao;

import com.hurpods.ssm.blog.models.Province;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProvinceDao {
    List<Province> getAllProvince();

    Province getProvinceById(String id);

    Province getProvinceByCode(String code);
}
