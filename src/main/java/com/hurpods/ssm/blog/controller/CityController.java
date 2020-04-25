package com.hurpods.ssm.blog.controller;

import com.alibaba.fastjson.JSON;
import com.hurpods.ssm.blog.models.City;
import com.hurpods.ssm.blog.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CityController {
    @Autowired
    CityService cityService;

    @RequestMapping(value = "/getCityByProvince", method = RequestMethod.GET)
    @ResponseBody
    public String getCityByProvince(@RequestParam(value="code")String code) {
        List<City> allCity = cityService.getCityByProvince(code);
        System.out.println(allCity);
        return JSON.toJSONString(allCity);
    }
}
