package com.hurpods.ssm.blog.service.impl;

import com.hurpods.ssm.blog.dao.ProvinceDao;
import com.hurpods.ssm.blog.models.Province;
import com.hurpods.ssm.blog.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
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
