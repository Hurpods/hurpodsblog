package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.dao.CityDao;
import com.hurpods.ssm.blog.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CityController {
    @Autowired
    CityDao cityDao;

    public List<City> getAllCity() {
        return cityDao.getAllCity();
    }

    public City getCityById(String id) {
        return cityDao.getCityById(id);
    }

    public City getCityByCode(String code) {
        return cityDao.getCityByCode(code);
    }

    public City getCityByProvince(String province) {
        return cityDao.getCityByProvince(province);
    }

    public String getCityCodeByProvince(String province) {
        return cityDao.getCityCodeByProvince(province);
    }
}
