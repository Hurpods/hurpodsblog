package com.hurpods.ssm.blog.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class City implements Serializable {
    private static final long serialVersionUID = -6289501844245451535L;
    //城市标识
    private String cityId;
    //城市唯一编码
    private String cityCode;
    //城市名
    private String cityName;
    //城市所属省份编码
    private String provinceCode;
}
