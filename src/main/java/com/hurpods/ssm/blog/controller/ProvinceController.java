package com.hurpods.ssm.blog.controller;

import com.hurpods.ssm.blog.dao.ProvinceDao;
import com.hurpods.ssm.blog.models.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProvinceController {
    @Autowired
    ProvinceDao provinceDao;

    public List<Province> getAllProvince() {
        return provinceDao.getAllProvince();
    }

    public Province getProvinceById(String id) {
        return provinceDao.getProvinceById(id);
    }

    public Province getProvinceByCode(String code) {
        return provinceDao.getProvinceByCode(code);
    }
}
