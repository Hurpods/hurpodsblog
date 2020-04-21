package com.hurpods.ssm.blog.service;

import com.hurpods.ssm.blog.models.Province;

import java.util.List;

public interface ProvinceService {
    List<Province> getAllProvince();

    Province getProvinceById(String id);

    Province getProvinceByCode(String code);
}
