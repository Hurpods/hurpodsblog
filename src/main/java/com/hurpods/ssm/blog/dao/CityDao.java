package com.hurpods.ssm.blog.dao;

import com.hurpods.ssm.blog.models.City;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CityDao {
    List<City> getAllCity();

    City getCityById(String id);

    City getCityByCode(String code);

    City getCityByProvince(String province);

    String getCityCodeByProvince(String province);
}
