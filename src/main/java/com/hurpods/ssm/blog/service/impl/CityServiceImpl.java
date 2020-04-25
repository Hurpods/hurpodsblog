package com.hurpods.ssm.blog.service.impl;

import com.hurpods.ssm.blog.dao.CityDao;
import com.hurpods.ssm.blog.models.City;
import com.hurpods.ssm.blog.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
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

    public List<City> getCityByProvince(String province) {
        return cityDao.getCityByProvince(province);
    }

    public String getCityCodeByProvince(String province) {
        return cityDao.getCityCodeByProvince(province);
    }
}
