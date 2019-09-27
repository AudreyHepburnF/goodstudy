package com.personalstudy.springcloud.springcloudconfig.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConfigClient {

    @Value("goodstudy")
    private String goodstudy;

    @RequestMapping("/getConfigDev")
    public String getConfigDev(){
        return goodstudy;
    }
}
