package com.personalstudy.springcloud.eurekaclient.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员服务
 */
@RestController
public class VipController {

    @RequestMapping("/getInfo")
    public String getInfo(){
        return  "this is a eureka-client demo";
    }
}
