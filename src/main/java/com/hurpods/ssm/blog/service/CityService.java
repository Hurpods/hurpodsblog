package com.hurpods.ssm.blog.service;

import com.hurpods.ssm.blog.models.City;
import java.util.List;

public interface CityService {
    List<City> getAllCity();

    City getCityById(String id);

    City getCityByCode(String code);

    City getCityByProvince(String province);

    String getCityCodeByProvince(String province);
}
