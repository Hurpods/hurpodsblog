package com.hurpods.ssm.blog.controller;

import com.alibaba.fastjson.JSON;
import com.hurpods.ssm.blog.models.Province;
import com.hurpods.ssm.blog.service.CityService;
import com.hurpods.ssm.blog.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProvinceController {
    @Autowired
    CityService cityService;

    @Autowired
    ProvinceService provinceService;

    @RequestMapping(value = "/getProvince", method = RequestMethod.GET)
    @ResponseBody
    public String getProvince() {
        List<Province> allProvince=provinceService.getAllProvince();
        return JSON.toJSONString(allProvince);
    }
}
