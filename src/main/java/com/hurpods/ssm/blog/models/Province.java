package com.hurpods.ssm.blog.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Province implements Serializable {
    private static final long serialVersionUID = 8393140307362176250L;
    //省份标识
    private String provinceId;
    //省份唯一编码
    private String provinceCode;
    //省份名称
    private String provinceName;
}
